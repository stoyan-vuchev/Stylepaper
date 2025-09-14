package com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model

import android.os.Parcelable
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpaperColor
import kotlinx.parcelize.Parcelize

@Parcelize
data class WallpapersListingItemModel(
    val id: String = "",
    val category: String = "",
    val colors: List<WallpaperColor> = listOf(),
    val createdAt: String = "",
    val dimensionX: Int = 0,
    val dimensionY: Int = 0,
    val favorites: Long = 0L,
    val fileSize: Long = 0L,
    val fileType: String = "",
    val path: String = "",
    val purity: String = "",
    val ratio: String = "",
    val resolution: String = "",
    val shortUrl: String = "",
    val source: String = "",
    val thumbnails: WallpaperThumbnailsModel = WallpaperThumbnailsModel(),
    val url: String = "",
    val views: Long = 0L
) : Parcelable