package com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WallpaperThumbnailsModel(
    val large: String = "",
    val original: String = "",
    val small: String = ""
) : Parcelable