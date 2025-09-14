package com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WallpaperUploaderModel(
    val avatar: WallpaperUploaderAvatarModel = WallpaperUploaderAvatarModel(),
    val group: String = "",
    val username: String = "",
) : Parcelable