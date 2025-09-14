package com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

@Keep
data class WallpaperResponseData(

    @SerializedName("category")
    val category: String,

    @SerializedName("colors")
    val colors: List<String>,

    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    val createdAt: String,

    @ColumnInfo(name = "dimension_x")
    @SerializedName("dimension_x")
    val dimensionX: Int,

    @ColumnInfo(name = "dimension_y")
    @SerializedName("dimension_y")
    val dimensionY: Int,

    @SerializedName("favorites")
    val favorites: Long,

    @ColumnInfo(name = "file_size")
    @SerializedName("file_size")
    val fileSize: Long,

    @ColumnInfo(name = "file_type")
    @SerializedName("file_type")
    val fileType: String,

    @SerializedName("id")
    val id: String,

    @SerializedName("path")
    val path: String,

    @SerializedName("purity")
    val purity: String,

    @SerializedName("ratio")
    val ratio: String,

    @SerializedName("resolution")
    val resolution: String,

    @ColumnInfo(name = "short_url")
    @SerializedName("short_url")
    val shortUrl: String,

    @SerializedName("source")
    val source: String,

    @SerializedName("tags")
    val tags: List<WallpaperResponseTag>,

    @SerializedName("thumbs")
    val thumbs: WallpaperResponseThumbnails,

    @SerializedName("uploader")
    val uploader: WallpaperResponseUploader,

    @SerializedName("url")
    val url: String,

    @SerializedName("views")
    val views: Long

)