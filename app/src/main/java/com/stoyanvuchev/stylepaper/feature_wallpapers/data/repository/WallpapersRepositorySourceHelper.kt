package com.stoyanvuchev.stylepaper.feature_wallpapers.data.repository

import com.stoyanvuchev.stylepaper.core.etc.Result
import com.stoyanvuchev.stylepaper.core.etc.UIString
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.local.WallpapersLocalDatabaseDao
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.WallpapersRemoteAPI
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response.WallpapersListingResponse
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpaperColor
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpapersCategory
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpapersHomeSelection
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpapersHomeSelection.Latest
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpapersHomeSelection.Popular
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpapersHomeSelection.Random
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model.WallpaperModel
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model.WallpapersListingModel
import com.stoyanvuchev.stylepaper.feature_wallpapers.mappers.toDiscoverEntity
import com.stoyanvuchev.stylepaper.feature_wallpapers.mappers.toHomeEntity
import com.stoyanvuchev.stylepaper.feature_wallpapers.mappers.toModel
import com.stoyanvuchev.stylepaper.feature_wallpapers.mappers.toSearchEntity
import org.threeten.bp.Duration
import org.threeten.bp.Instant
import retrofit2.Response
import javax.inject.Inject

/**
 *
 *  This helper class was created with the intention
 *  of minimizing duplicate code inside WallpapersRepositoryImpl.
 *
 * */

class WallpapersRepositorySourceHelper @Inject constructor(
    private val api: WallpapersRemoteAPI,
    val dao: WallpapersLocalDatabaseDao
) {

    suspend fun deleteWallpapersListing(
        listingType: ListingType,
        page: Int
    ): Result<Unit> {
        return try {
            when (listingType) {

                is ListingType.Home -> dao.deleteHomeWallpapersListing(
                    selection = listingType.selection,
                    page = page
                )

                is ListingType.Discover -> dao.deleteDiscoverWallpapersListing(
                    category = listingType.category,
                    color = listingType.color,
                    page = page
                )

                is ListingType.Search -> dao.deleteSearchWallpapersListing(
                    searchQuery = listingType.searchQuery,
                    category = listingType.category,
                    color = listingType.color,
                    page = page
                )

            }
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(
                error = UIString.Basic(
                    e.localizedMessage ?: "Something went wrong."
                )
            )
        }
    }

    suspend fun deleteWallpapersById(wallpaperId: String): Result<Unit> {
        return try {
            dao.deleteWallpaperById(wallpaperId)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(
                error = UIString.Basic(e.localizedMessage ?: "Something went wrong.")
            )
        }
    }

    suspend fun getWallpapersListing(
        listingType: ListingType,
        page: Int,
        seed: String = ""
    ): Result<WallpapersListingModel> {
        return try {

            when (listingType) {

                is ListingType.Home -> {

                    val homeWallpapersResponse = when (listingType.selection) {
                        Latest -> api.fetchLatestHomeWallpapersListing(page = page)
                        Popular -> api.fetchPopularHomeWallpapersListing(page = page)
                        Random -> api.fetchRandomHomeWallpapersListing(page = page, seed = seed)
                    }

                    fetchWallpapersListingFromApiAndInsertToDB(
                        response = homeWallpapersResponse,
                        page = page,
                        listingType = listingType
                    )

                }

                is ListingType.Discover -> {

                    val discoverWallpapersResponse = api.fetchDiscoverWallpapersListing(
                        category = listingType.category.value,
                        colors = listingType.color.hexColor,
                        page = page
                    )

                    fetchWallpapersListingFromApiAndInsertToDB(
                        response = discoverWallpapersResponse,
                        page = page,
                        listingType = listingType
                    )

                }

                is ListingType.Search -> {

                    val searchWallpapersResponse = api.searchWallpapersListing(
                        categoryAndQ = "${listingType.category}%20$${listingType.searchQuery}",
                        colors = listingType.color.hexColor,
                        page = page
                    )

                    fetchWallpapersListingFromApiAndInsertToDB(
                        response = searchWallpapersResponse,
                        page = page,
                        listingType = listingType
                    )

                }

            }

        } catch (e: Exception) {
            Result.Error(
                error = UIString.Basic(e.localizedMessage ?: "Something went wrong.")
            )
        }
    }

    suspend fun fetchWallpaperFromApiAndInsertToDB(wallpaperId: String): Result<WallpaperModel> {
        return try {

            val response = api.fetchWallpaperById(wallpaperId)
            val responseBody = response.body()

            if (response.isSuccessful && responseBody != null) {

                val fetched = System.currentTimeMillis()
                dao.upsertWallpaper(responseBody.toHomeEntity(fetched))

                val wallpaperEntity = dao.getWallpaperById(wallpaperId)
                if (wallpaperEntity != null) {
                    Result.Success(wallpaperEntity.toModel())
                } else {
                    Result.Error(error = UIString.Basic("Something went wrong."))
                }

            } else {
                Result.Error(error = UIString.Basic("Something went wrong."))
            }

        } catch (e: Exception) {
            Result.Error(
                error = UIString.Basic(
                    e.localizedMessage ?: "Something went wrong."
                )
            )
        }
    }

    private suspend fun fetchWallpapersListingFromApiAndInsertToDB(
        response: Response<WallpapersListingResponse>,
        page: Int,
        listingType: ListingType
    ): Result<WallpapersListingModel> {

        val responseBody = response.body()

        return if (response.isSuccessful && responseBody != null) {

            val fetched = System.currentTimeMillis()

            when (listingType) {

                is ListingType.Home -> {
                    dao.upsertHomeWallpapersListing(
                        responseBody.toHomeEntity(
                            fetched,
                            listingType.selection
                        )
                    )
                }

                is ListingType.Discover -> {
                    dao.upsertDiscoverWallpapersListing(
                        responseBody.toDiscoverEntity(
                            fetched,
                            listingType.category,
                            listingType.color
                        )
                    )
                }

                is ListingType.Search -> {
                    dao.upsertSearchWallpapersListing(
                        responseBody.toSearchEntity(
                            fetched,
                            listingType.searchQuery,
                            listingType.category,
                            listingType.color
                        )
                    )
                }

            }

            val wallpapersListingModel = when (listingType) {

                is ListingType.Home -> dao.getHomeWallpapersListing(
                    listingType.selection,
                    page
                )?.toModel()

                is ListingType.Discover -> dao.getDiscoverWallpapersListing(
                    listingType.category,
                    listingType.color,
                    page
                )?.toModel()

                is ListingType.Search -> dao.getSearchWallpapersListing(
                    listingType.searchQuery,
                    listingType.category,
                    listingType.color,
                    page
                )?.toModel()

            }

            wallpapersListingModel.let {

                if (it != null) {
                    Result.Success(it)
                } else {
                    Result.Error(error = UIString.Basic("Something went wrong."))
                }

            }

        } else {

            val error = UIString.Basic(
                if (response.code() == 429) {
                    "You've scrolled through a lot of wallpapers. Try again in a minute. 😴💤"
                } else "Something went wrong. Error code: ${response.code()}"
            )

            return Result.Error(error = error)

        }

    }

    companion object {

        fun Long.moreThanOffsetMinutesAgo(offset: Int): Boolean {

            val lastFetchedMillis = Instant.ofEpochMilli(this).toEpochMilli()
            val lastFetched = Duration.ofMillis(lastFetchedMillis).toMinutes()

            val now = Instant.now().toEpochMilli()
            val offsetMinutesAgo = Duration.ofMillis(now).toMinutes().minus(offset)

            return lastFetched <= offsetMinutesAgo

        }

        sealed interface ListingType {

            data class Home(val selection: WallpapersHomeSelection) : ListingType

            data class Discover(
                val category: WallpapersCategory,
                val color: WallpaperColor
            ) : ListingType

            data class Search(
                val searchQuery: String,
                val category: WallpapersCategory,
                val color: WallpaperColor
            ) : ListingType

        }

    }

}