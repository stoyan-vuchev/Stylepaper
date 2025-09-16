package com.stoyanvuchev.stylepaper.core.ui.navhost

import androidx.navigation.NavBackStackEntry
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.Screen

fun NavBackStackEntry.safeScreen(): Screen? {
    return when (destination.route) {
        Screen.WallpapersNavigation::class.qualifiedName -> Screen.WallpapersNavigation
        Screen.Home::class.qualifiedName -> Screen.Home
        Screen.Discover::class.qualifiedName -> Screen.Discover
        Screen.Search::class.qualifiedName -> Screen.Search
        Screen.Menu::class.qualifiedName -> Screen.Menu

        // For detail screens, we usually don’t care about args during transitions
        Screen.WallpaperDetails::class.qualifiedName -> Screen.WallpaperDetails(
            "placeholder",
            "placeholder"
        )

        Screen.ApplyWallpaper::class.qualifiedName -> Screen.ApplyWallpaper("placeholder")
        else -> null

    }
}
