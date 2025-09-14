package com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote

import com.stoyanvuchev.stylepaper.BuildConfig
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response.WallpaperResponse
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response.WallpapersListingResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WallpapersRemoteAPI {

    companion object {
        val retrofitInstance: Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @GET(BuildConfig.WALLPAPERS_LISTING_ENDPOINT)
    suspend fun fetchLatestHomeWallpapersListing(
        @Query("q") category: String = "",
        @Query("sorting") sorting: String = "date_added",
        @Query("order") order: String = "desc",
        @Query("atleast") atLeast: String = "1080x1980",
        @Query("ratios") ratios: String = "3x4,4x3,9x16,16x9",
        @Query("colors") colors: String = "",
        @Query("seed") seed: String = "",
        @Query("page") page: Int = 1
    ): Response<WallpapersListingResponse>

    @GET(BuildConfig.WALLPAPERS_LISTING_ENDPOINT)
    suspend fun fetchPopularHomeWallpapersListing(
        @Query("q") category: String = "",
        @Query("sorting") sorting: String = "views",
        @Query("order") order: String = "desc",
        @Query("atleast") atLeast: String = "1080x1980",
        @Query("ratios") ratios: String = "3x4,4x3,9x16,16x9",
        @Query("colors") colors: String = "",
        @Query("seed") seed: String = "",
        @Query("page") page: Int = 1
    ): Response<WallpapersListingResponse>

    @GET(BuildConfig.WALLPAPERS_LISTING_ENDPOINT)
    suspend fun fetchRandomHomeWallpapersListing(
        @Query("q") category: String = "",
        @Query("sorting") sorting: String = "random",
        @Query("order") order: String = "desc",
        @Query("atleast") atLeast: String = "1080x1980",
        @Query("ratios") ratios: String = "3x4,4x3,9x16,16x9",
        @Query("colors") colors: String = "",
        @Query("seed") seed: String = "",
        @Query("page") page: Int = 1
    ): Response<WallpapersListingResponse>

    @GET(BuildConfig.WALLPAPERS_LISTING_ENDPOINT)
    suspend fun fetchDiscoverWallpapersListing(
        @Query("q") category: String = "",
        @Query("sorting") sorting: String = "random",
        @Query("order") order: String = "desc",
        @Query("atleast") atLeast: String = "1080x1980",
        @Query("ratios") ratios: String = "3x4,4x3,9x16,16x9",
        @Query("colors") colors: String = "",
        @Query("seed") seed: String = "",
        @Query("page") page: Int = 1
    ): Response<WallpapersListingResponse>

    @GET(BuildConfig.SEARCH_WALLPAPERS_LISTING_ENDPOINT)
    suspend fun searchWallpapersListing(
        @Query("q") categoryAndQ: String = "",
        @Query("sorting") sorting: String = "date_added",
        @Query("order") order: String = "desc",
        @Query("atleast") atLeast: String = "1080x1980",
        @Query("ratios") ratios: String = "3x4,4x3,9x16,16x9",
        @Query("colors") colors: String = "",
        @Query("seed") seed: String = "",
        @Query("page") page: Int = 1
    ): Response<WallpapersListingResponse>

    @GET(BuildConfig.WALLPAPER_BY_ID_ENDPOINT)
    suspend fun fetchWallpaperById(
        @Path("wallpaperId") wallpaperId: String
    ): Response<WallpaperResponse>

}