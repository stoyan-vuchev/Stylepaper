package com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WallpaperUploaderAvatarModel(
    val large: String = "",
    val medium: String = "",
    val small: String = "",
    val tiny: String = "",
) : Parcelable