package com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpaper_details

import androidx.compose.runtime.Immutable

@Immutable
sealed interface WallpaperDetailsScreenUIAction {
    object Back : WallpaperDetailsScreenUIAction
    object Fullscreen : WallpaperDetailsScreenUIAction
    object ScrollToTop : WallpaperDetailsScreenUIAction
    object Download : WallpaperDetailsScreenUIAction
    object Apply : WallpaperDetailsScreenUIAction
    object Save : WallpaperDetailsScreenUIAction
}