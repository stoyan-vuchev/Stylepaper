package com.stoyanvuchev.stylepaper.feature_wallpapers.data.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.stoyanvuchev.stylepaper.core.etc.Result
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.local.WallpapersLocalDatabase
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.WallpapersRemoteAPI
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpaperColor
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpapersCategory
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpapersHomeSelection
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.repository.WallpapersRepository
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WallpapersRepositoryTest {

    private lateinit var db: WallpapersLocalDatabase
    private lateinit var repository: WallpapersRepository

    @Before
    fun setUp() {

        // Initialize the Application Context required for the Database
        val context = ApplicationProvider.getApplicationContext<Context>()

        // Initialize the remote API
        val api = WallpapersRemoteAPI.retrofitInstance.create(WallpapersRemoteAPI::class.java)

        // Initialize the Database
        db = Room.inMemoryDatabaseBuilder(context, WallpapersLocalDatabase::class.java).build()

        // Initialize the WallpapersRepositorySourceHelper
        val sourceHelper = WallpapersRepositorySourceHelper(api, db.dao)

        // Initialize the Repository
        repository = WallpapersRepositoryImpl(sourceHelper)

    }

    @After
    fun closeDb() {
        // Closes the Database after completing the tests
        db.close()
    }

    @Test
    fun fetchWallpapersListing() = runTest {
        try {
            val result = repository.loadHomeWallpapersListing(
                selection = WallpapersHomeSelection.Latest,
                page = 1
            )
            when (result) {
                is Result.Success -> assertThat(result.data).isNotNull()
                else -> Unit
            }
        } catch (e: Exception) {
            throw e
        }
    }

    @Test
    fun fetchDiscoverWallpapers() = runTest {
        try {

            val result = repository.loadDiscoverWallpapersListings(
                category = WallpapersCategory.Abstract,
                color = WallpaperColor.CyanColor,
                page = 1
            )

            when (result) {
                is Result.Success -> assertThat(result.data).isNotNull()
                else -> Unit
            }

        } catch (e: Exception) {
            throw e
        }
    }

    @Test
    fun searchForWallpapers() = runTest {
        try {

            val result = repository.searchForWallpapers(
                searchQuery = "Abstract",
                category = WallpapersCategory.Abstract,
                color = WallpaperColor.CyanColor,
                page = 1
            )

            when (result) {
                is Result.Success -> assertThat(result.data).isNotNull()
                else -> Unit
            }

        } catch (e: Exception) {
            throw e
        }
    }

    @Test
    fun fetchWallpaperById() = runTest(StandardTestDispatcher()) {
        try {
            val wallpaperId = "x65xed"
            when (val result = repository.loadWallpaperById(wallpaperId)) {
                is Result.Success -> assertThat(result.data).isNotNull()
                else -> Unit
            }
        } catch (e: Exception) {
            throw e
        }
    }

}