package com.stoyanvuchev.stylepaper.feature_wallpapers.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.local.entity.WallpaperEntity
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.local.entity.WallpapersDiscoverListingEntity
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.local.entity.WallpapersHomeListingEntity
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.local.entity.WallpapersSearchListingEntity
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpapersCategory
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpapersHomeSelection

@Dao
interface WallpapersLocalDatabaseDao {

    // Home Wallpapers Listing Entities

    @Insert(
        onConflict = OnConflictStrategy.REPLACE,
        entity = WallpapersHomeListingEntity::class
    )
    suspend fun upsertHomeWallpapersListing(
        wallpapersHomeListingEntity: WallpapersHomeListingEntity
    )

    @Query(
        "SELECT * FROM wallpapers_home_table " +
                "WHERE selection = :selection " +
                "AND current_page = :page"
    )
    suspend fun getHomeWallpapersListing(
        selection: WallpapersHomeSelection,
        page: Int
    ): WallpapersHomeListingEntity?

    @Query(
        "DELETE FROM wallpapers_home_table " +
                "WHERE selection = :selection " +
                "AND current_page = :page"
    )
    suspend fun deleteHomeWallpapersListing(
        selection: WallpapersHomeSelection,
        page: Int
    )

    // Discover Wallpapers Listing Entities

    @Insert(
        onConflict = OnConflictStrategy.REPLACE,
        entity = WallpapersDiscoverListingEntity::class
    )
    suspend fun upsertDiscoverWallpapersListing(
        wallpapersDiscoverListingEntity: WallpapersDiscoverListingEntity
    )

    @Query(
        "SELECT * FROM wallpapers_discover_table " +
                "WHERE category = :category " +
                "AND color = :color " +
                "AND current_page = :page"
    )
    suspend fun getDiscoverWallpapersListing(
        category: WallpapersCategory,
        color: String,
        page: Int
    ): WallpapersDiscoverListingEntity?

    @Query(
        "DELETE FROM wallpapers_discover_table " +
                "WHERE category = :category " +
                "AND color = :color " +
                "AND current_page = :page"
    )
    suspend fun deleteDiscoverWallpapersListing(
        category: WallpapersCategory,
        color: String,
        page: Int
    )

    // Search Wallpapers Listing Entities

    @Insert(
        onConflict = OnConflictStrategy.REPLACE,
        entity = WallpapersSearchListingEntity::class
    )
    suspend fun upsertSearchWallpapersListing(
        wallpapersSearchListingEntity: WallpapersSearchListingEntity
    )

    @Query(
        "SELECT * FROM wallpapers_search_table " +
                "WHERE search_query = :searchQuery " +
                "AND category = :category " +
                "AND color = :color " +
                "AND current_page = :page"
    )
    suspend fun getSearchWallpapersListing(
        searchQuery: String,
        category: WallpapersCategory,
        color: String,
        page: Int
    ): WallpapersSearchListingEntity?

    @Query(
        "DELETE FROM wallpapers_search_table " +
                "WHERE search_query = :searchQuery " +
                "AND category = :category " +
                "AND color = :color " +
                "AND current_page = :page"
    )
    suspend fun deleteSearchWallpapersListing(
        searchQuery: String,
        category: WallpapersCategory,
        color: String,
        page: Int
    )

    // Wallpaper Entity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertWallpaper(wallpaperEntity: WallpaperEntity)

    @Query("SELECT * FROM WallpaperEntity WHERE id = :id")
    suspend fun getWallpaperById(id: String): WallpaperEntity?

    @Query("DELETE FROM WallpaperEntity WHERE id = :id")
    suspend fun deleteWallpaperById(id: String)

}