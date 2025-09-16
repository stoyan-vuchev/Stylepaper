package com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpaper_details

import android.os.Parcelable
import androidx.compose.runtime.Stable
import com.stoyanvuchev.stylepaper.core.etc.UIString
import kotlinx.parcelize.Parcelize
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model.WallpaperModel

@Parcelize
@Stable
data class WallpaperDetailsScreenState(
    val isLoading: Boolean = true,
    val isDownloading: Boolean = false,
    val wallpaper: WallpaperModel = WallpaperModel(),
    val wallpaperId: String = "",
    val error: UIString? = null
) : Parcelable