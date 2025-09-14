package com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote

import com.google.common.truth.Truth.assertThat
import com.stoyanvuchev.stylepaper.BuildConfig
import kotlinx.coroutines.test.runTest
import org.junit.Test

class WallpapersRemoteAPITest {

    @Test
    fun `WallpapersRemoteAPI Retrofit instance base url matches BuildConfig's base url`() {

        // Get an Retrofit instance of WallpapersRemoteAPI
        val instance = WallpapersRemoteAPI.retrofitInstance

        // Assert that WallpapersRemoteAPI base url matches BuildConfig's base url
        assertThat(instance.baseUrl().toUrl().toString()).isEqualTo(BuildConfig.BASE_URL)

    }

    @Test
    fun `WallpapersRemoteAPI Latest endpoint returns successful response`() = runTest {

        // Get an instance of WallpapersRemoteAPI
        val remoteDataSource = WallpapersRemoteAPI.retrofitInstance
            .create(WallpapersRemoteAPI::class.java)

        // Assert that the response from the endpoint is successful
        val response = remoteDataSource.fetchLatestHomeWallpapersListing()
        assertThat(response.isSuccessful).isTrue()

    }

    @Test
    fun `WallpapersRemoteAPI Popular endpoint returns successful response`() = runTest {

        // Get an instance of WallpapersRemoteAPI
        val remoteDataSource = WallpapersRemoteAPI.retrofitInstance
            .create(WallpapersRemoteAPI::class.java)

        // Assert that the response from the endpoint is successful
        val response = remoteDataSource.fetchPopularHomeWallpapersListing()
        assertThat(response.isSuccessful).isTrue()

    }

    @Test
    fun `WallpapersRemoteAPI Random endpoint returns successful response`() = runTest {

        // Get an instance of WallpapersRemoteAPI
        val remoteDataSource = WallpapersRemoteAPI.retrofitInstance
            .create(WallpapersRemoteAPI::class.java)

        // Assert that the response from the endpoint is successful
        val response = remoteDataSource.fetchRandomHomeWallpapersListing()
        assertThat(response.isSuccessful).isTrue()

    }

    @Test
    fun `WallpapersRemoteAPI Discover endpoint returns successful response`() = runTest {

        // Get an instance of WallpapersRemoteAPI
        val remoteDataSource = WallpapersRemoteAPI.retrofitInstance
            .create(WallpapersRemoteAPI::class.java)

        // Assert that the response from the endpoint is successful
        val response = remoteDataSource.fetchDiscoverWallpapersListing(colors = "66cccc")
        assertThat(response.isSuccessful).isTrue()

    }

    @Test
    fun `WallpapersRemoteAPI Search endpoint returns successful response`() = runTest {

        // Get an instance of WallpapersRemoteAPI
        val remoteDataSource = WallpapersRemoteAPI.retrofitInstance
            .create(WallpapersRemoteAPI::class.java)

        // Assert that the response from the endpoint is successful
        val response = remoteDataSource.searchWallpapersListing(categoryAndQ = "Bliss")
        assertThat(response.isSuccessful).isTrue()

    }

    @Test
    fun `WallpapersRemoteAPI By Id endpoint returns successful response`() = runTest {

        // Get an instance of WallpapersRemoteAPI
        val remoteDataSource = WallpapersRemoteAPI.retrofitInstance
            .create(WallpapersRemoteAPI::class.java)

        // Wallpaper ID
        val wallpaperId = "x65xed"

        // Assert successful endpoint response with expected result
        val response = remoteDataSource.fetchWallpaperById(wallpaperId)
        val id = response.body()?.data?.id
        assertThat(response.isSuccessful && id == wallpaperId).isTrue()

    }

}