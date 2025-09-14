package com.stoyanvuchev.stylepaper.feature_wallpapers.domain.repository

import com.stoyanvuchev.stylepaper.core.etc.Result
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpaperColor
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpapersCategory
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpapersHomeSelection
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model.WallpaperModel
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model.WallpapersListingModel

interface WallpapersRepository {

    suspend fun loadHomeWallpapersListing(
        selection: WallpapersHomeSelection,
        page: Int,
        seed: String = ""
    ): Result<WallpapersListingModel>

    suspend fun loadDiscoverWallpapersListings(
        category: WallpapersCategory,
        color: WallpaperColor,
        page: Int
    ): Result<WallpapersListingModel>

    suspend fun searchForWallpapers(
        searchQuery: String,
        category: WallpapersCategory,
        color: WallpaperColor,
        page: Int
    ): Result<WallpapersListingModel>

    suspend fun loadWallpaperById(
        wallpaperId: String
    ): Result<WallpaperModel>

}