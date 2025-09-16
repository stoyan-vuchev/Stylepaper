package com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model

import android.os.Parcelable
import androidx.compose.runtime.Stable
import kotlinx.parcelize.Parcelize

@Stable
@Parcelize
data class WallpaperModel(
    val id: String = "",
    val fetched: String = "",
    val category: String = "",
    val colors: List<String> = listOf(),
    val createdAt: String = "",
    val dimensionX: Int = 0,
    val dimensionY: Int = 0,
    val favorites: Long = 0L,
    val fileSize: String = "",
    val fileType: String = "",
    val aspectRatio: String = "",
    val path: String = "",
    val purity: String = "",
    val ratio: String = "",
    val resolution: String = "",
    val shortUrl: String = "",
    val source: String = "",
    val tags: List<WallpaperTagModel> = listOf(),
    val thumbs: WallpaperThumbnailsModel = WallpaperThumbnailsModel(),
    val uploader: WallpaperUploaderModel = WallpaperUploaderModel(),
    val url: String = "",
    val views: Int = 0
) : Parcelable