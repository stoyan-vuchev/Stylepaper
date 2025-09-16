package com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model

import android.os.Parcelable
import androidx.compose.runtime.Stable
import kotlinx.parcelize.Parcelize

@Stable
@Parcelize
data class WallpaperThumbnailsModel(
    val large: String = "",
    val original: String = "",
    val small: String = ""
) : Parcelable