package com.stoyanvuchev.stylepaper.feature_wallpapers.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.HttpException
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.WallpapersRemoteAPI
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response.WallpaperResponse
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response.WallpapersListingResponse
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpapersHomeSelection
import com.stoyanvuchev.stylepaper.feature_wallpapers.mappers.toHomeEntity

@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class WallpapersLocalDatabaseTest {

    private lateinit var api: WallpapersRemoteAPI
    private lateinit var db: WallpapersLocalDatabase
    private lateinit var dao: WallpapersLocalDatabaseDao

    private val wallpaperId = "x65xed"

    @Before
    fun setUp() {

        // Initialize the Application Context required for the Database
        val context = ApplicationProvider.getApplicationContext<Context>()

        // Initialize the remote API
        api = WallpapersRemoteAPI.retrofitInstance.create(WallpapersRemoteAPI::class.java)

        // Initialize the Database
        db = Room.inMemoryDatabaseBuilder(context, WallpapersLocalDatabase::class.java).build()

        // Initialize the Data Access Object (DAO)
        dao = db.dao

    }

    @After
    fun closeDb() {

        // Closes the Database after completing the tests
        db.close()

    }

    @Test
    fun writeAndReadWallpapersListingResponse() = runTest(StandardTestDispatcher()) {

        // Get WallpapersListingResponse from the remote API
        val response = getWallpapersListingResponseFromAPI()

        // If the Response is not NULL perform the Assertion
        response?.let { wallpapersListingResponse ->

            val fetched = System.currentTimeMillis()
            val selection = WallpapersHomeSelection.Latest

            // Map the Response to an Entity
            val wallpapersListingResponseEntity = wallpapersListingResponse
                .toHomeEntity(fetched = fetched, selection = selection)

            // Insert the Entity
            dao.upsertHomeWallpapersListing(wallpapersListingResponseEntity)

            // Query the Entity
            val wallpapersListingEntity = dao.getHomeWallpapersListing(selection, 1)

            // Assert that the Inserted Entity is the same as the Queried Entity
            withContext(Dispatchers.Main) {
                assertThat(wallpapersListingEntity)
                    .isEqualTo(wallpapersListingResponseEntity)
            }

        } ?: throw Exception()

    }

    @Test
    fun writeAndReadWallpaperResponse() = runTest(StandardTestDispatcher()) {

        // Get WallpaperResponse from the remote API
        val response = getWallpaperResponseFromAPI(wallpaperId)

        // If the Response is not NULL perform the Assertion
        response?.let { wallpaperResponse ->

            val wallpaperResponseEntity = wallpaperResponse
                .toHomeEntity(System.currentTimeMillis())

            // Insert the Entity
            dao.upsertWallpaper(wallpaperResponseEntity)

            // Query the Entity
            val wallpaperEntity = dao.getWallpaperById(wallpaperId)

            // Assert that the Inserted Entity is the same as the Queried Entity
            withContext(Dispatchers.Main) {
                assertThat(wallpaperEntity)
                    .isEqualTo(wallpaperResponseEntity)
            }

        } ?: throw Exception()

    }

    @Test
    fun writeAndDeleteWallpapersListingEntity() = runTest(StandardTestDispatcher()) {

        // Try deleting an existing Wallpapers Listing Entity
        try {

            // Delete the wallpapers listing entities
            dao.deleteHomeWallpapersListing(selection = WallpapersHomeSelection.Latest, 1)

            // Attempt to get wallpapers listing entities
            val wallpapersListingEntity = dao.getHomeWallpapersListing(
                WallpapersHomeSelection.Latest, 1
            )

            // Assert that the wallpapers listing entities are null (nonexistent)
            withContext(Dispatchers.Main) {
                assertThat(wallpapersListingEntity).isEqualTo(null)
            }

        } catch (e: Exception) {
            throw e
        }

    }

    @Test
    fun writeAndDeleteWallpaperEntity() = runTest(StandardTestDispatcher()) {

        // Try deleting an existing Wallpaper Entity
        try {

            // Delete the wallpaper entity
            dao.deleteWallpaperById(wallpaperId)

            // Attempt to get wallpapers entity
            val wallpapersEntity = dao.getWallpaperById(wallpaperId)

            // Assert that the wallpaper entity is null (nonexistent)
            withContext(Dispatchers.Main) {
                assertThat(wallpapersEntity).isEqualTo(null)
            }

        } catch (e: Exception) {
            throw e
        }

    }

    private suspend fun getWallpapersListingResponseFromAPI(): WallpapersListingResponse? {

        // Try obtaining a response from the Remote API Endpoint
        return try {

            val response = api.fetchLatestHomeWallpapersListing()
            val data = response.body()?.data
            val metadata = response.body()?.metadata

            if (response.isSuccessful && data != null && metadata != null) {
                WallpapersListingResponse(data = data, metadata = metadata)
            } else throw HttpException(response)

        } catch (e: Exception) {
            throw e
        }

    }

    private suspend fun getWallpaperResponseFromAPI(wallpaperId: String): WallpaperResponse? {

        // Try obtaining a response from the Remote API Endpoint
        return try {

            val response = api.fetchWallpaperById(wallpaperId)
            val data = response.body()?.data

            if (response.isSuccessful && data != null) {
                WallpaperResponse(data = data)
            } else throw HttpException(response)

        } catch (e: Exception) {
            throw e
        }

    }

}