package com.stoyanvuchev.stylepaper.feature_wallpapers.data.repository

import com.stoyanvuchev.stylepaper.core.etc.Result
import com.stoyanvuchev.stylepaper.core.etc.UIString
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.repository.WallpapersRepositorySourceHelper.Companion.ListingType
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.repository.WallpapersRepositorySourceHelper.Companion.moreThanOffsetMinutesAgo
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpaperColor
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpapersCategory
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpapersHomeSelection
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model.WallpaperModel
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model.WallpapersListingModel
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.repository.WallpapersRepository
import com.stoyanvuchev.stylepaper.feature_wallpapers.mappers.toModel
import javax.inject.Inject

class WallpapersRepositoryImpl @Inject constructor(
    private val source: WallpapersRepositorySourceHelper
) : WallpapersRepository {

    private val lastFetchedOffset = 10

    override suspend fun loadHomeWallpapersListing(
        selection: WallpapersHomeSelection,
        page: Int,
        seed: String
    ): Result<WallpapersListingModel> {
        try {

            val wallpapersListingEntity = source.dao.getHomeWallpapersListing(selection, page)
            val listingType = ListingType.Home(selection = selection)

            return if (wallpapersListingEntity != null) {

                if (wallpapersListingEntity.fetched.moreThanOffsetMinutesAgo(lastFetchedOffset)) {

                    when (val deletionResult = source.deleteWallpapersListing(listingType, page)) {
                        is Result.Success -> source.getWallpapersListing(listingType, page, seed)
                        else -> Result.Error(error = deletionResult.error)
                    }

                } else Result.Success(wallpapersListingEntity.toModel())

            } else source.getWallpapersListing(listingType, page, seed)

        } catch (e: Exception) {
            return Result.Error(
                error = UIString.Basic(e.localizedMessage ?: "Something went wrong.")
            )
        }
    }

    override suspend fun loadDiscoverWallpapersListings(
        category: WallpapersCategory,
        color: WallpaperColor,
        page: Int
    ): Result<WallpapersListingModel> {
        try {

            val wallpapersListingEntity = source.dao.getDiscoverWallpapersListing(
                category = category,
                color = color,
                page = page
            )

            val listingType = ListingType.Discover(
                category = category,
                color = color
            )

            return if (wallpapersListingEntity != null) {

                if (wallpapersListingEntity.fetched.moreThanOffsetMinutesAgo(lastFetchedOffset)) {

                    when (val deletionResult = source.deleteWallpapersListing(listingType, page)) {
                        is Result.Success -> source.getWallpapersListing(listingType, page)
                        else -> Result.Error(error = deletionResult.error)
                    }

                } else Result.Success(wallpapersListingEntity.toModel())

            } else source.getWallpapersListing(listingType, page)

        } catch (e: Exception) {
            return Result.Error(
                error = UIString.Basic(e.localizedMessage ?: "Something went wrong.")
            )
        }
    }

    override suspend fun searchForWallpapers(
        searchQuery: String,
        category: WallpapersCategory,
        color: WallpaperColor,
        page: Int
    ): Result<WallpapersListingModel> {
        try {

            val wallpapersListingEntity = source.dao.getSearchWallpapersListing(
                searchQuery = searchQuery,
                category = category,
                color = color,
                page
            )

            val listingType = ListingType.Search(
                searchQuery = searchQuery,
                category = category,
                color = color
            )

            return if (wallpapersListingEntity != null) {

                if (wallpapersListingEntity.fetched.moreThanOffsetMinutesAgo(lastFetchedOffset)) {

                    when (val deletionResult = source.deleteWallpapersListing(listingType, page)) {
                        is Result.Success -> source.getWallpapersListing(listingType, page)
                        else -> Result.Error(error = deletionResult.error)
                    }

                } else Result.Success(wallpapersListingEntity.toModel())

            } else source.getWallpapersListing(listingType, page)

        } catch (e: Exception) {
            return Result.Error(
                error = UIString.Basic(e.localizedMessage ?: "Something went wrong.")
            )
        }
    }

    override suspend fun loadWallpaperById(wallpaperId: String): Result<WallpaperModel> {
        return try {

            val wallpaper = source.dao.getWallpaperById(wallpaperId)

            if (wallpaper != null) {

                if (wallpaper.fetched.moreThanOffsetMinutesAgo(lastFetchedOffset)) {

                    when (val deletionResult = source.deleteWallpapersById(wallpaperId)) {

                        is Result.Success -> {
                            source.fetchWallpaperFromApiAndInsertToDB(wallpaperId)
                        }

                        else -> Result.Error(error = deletionResult.error)

                    }

                } else {
                    Result.Success(wallpaper.toModel())
                }

            } else {
                source.fetchWallpaperFromApiAndInsertToDB(wallpaperId)
            }

        } catch (e: Exception) {
            Result.Error(
                error = UIString.Basic(e.localizedMessage ?: "Something went wrong.")
            )
        }
    }

}