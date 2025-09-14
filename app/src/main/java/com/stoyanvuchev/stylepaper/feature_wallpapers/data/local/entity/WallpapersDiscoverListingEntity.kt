package com.stoyanvuchev.stylepaper.feature_wallpapers.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response.WallpapersListingResponseItem
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpapersCategory

@Entity(
    tableName = "wallpapers_discover_table",
    primaryKeys = ["category", "color", "current_page"]
)
data class WallpapersDiscoverListingEntity(

    val fetched: Long,
    val category: WallpapersCategory,
    val color: String,
    val wallpapers: List<WallpapersListingResponseItem>,

    @ColumnInfo(name = "current_page")
    val currentPage: Int,

    @ColumnInfo(name = "last_page")
    val lastPage: Int,

    @ColumnInfo(name = "per_page")
    val perPage: String,

    val seed: String,
    val total: Int

)