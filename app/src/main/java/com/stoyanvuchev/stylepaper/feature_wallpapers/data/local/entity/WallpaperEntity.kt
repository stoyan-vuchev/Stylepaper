package com.stoyanvuchev.stylepaper.feature_wallpapers.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response.WallpaperResponseTag
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response.WallpaperResponseThumbnails
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response.WallpaperResponseUploader

@Entity
data class WallpaperEntity(

    @PrimaryKey
    val id: String,

    val fetched: Long,
    val category: String,
    val colors: List<String>,

    @ColumnInfo(name = "created_at")
    val createdAt: String,

    @ColumnInfo(name = "dimension_x")
    val dimensionX: Int,

    @ColumnInfo(name = "dimension_y")
    val dimensionY: Int,

    val favorites: Long,

    @ColumnInfo(name = "file_size")
    val fileSize: Long,

    @ColumnInfo(name = "file_type")
    val fileType: String,

    val path: String,
    val purity: String,
    val ratio: String,
    val resolution: String,

    @ColumnInfo(name = "short_url")
    val shortUrl: String,

    val source: String,
    val tags: List<WallpaperResponseTag>,
    val thumbs: WallpaperResponseThumbnails,
    val uploader: WallpaperResponseUploader,
    val url: String,
    val views: Long

)