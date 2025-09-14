package com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WallpaperTagModel(
    val id: Int = 0,
    val alias: String = "",
    val category: String = "",
    val createdAt: String = "",
    val name: String = "",
    val purity: String = ""
) : Parcelable