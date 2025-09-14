package com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.home

import androidx.compose.runtime.Stable
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpapersHomeSelection

@Stable
sealed interface WallpapersHomeScreenUIAction {
    object Search : WallpapersHomeScreenUIAction
    object ScrollToTop : WallpapersHomeScreenUIAction
    object LoadNextItems : WallpapersHomeScreenUIAction
    data class Selection(val selection: WallpapersHomeSelection) : WallpapersHomeScreenUIAction
    data class ViewWallpaper(val wallpaperId: String) : WallpapersHomeScreenUIAction
}