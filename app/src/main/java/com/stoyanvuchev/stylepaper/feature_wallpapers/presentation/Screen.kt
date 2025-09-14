package com.stoyanvuchev.stylepaper.feature_wallpapers.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.stoyanvuchev.stylepaper.R
import com.stoyanvuchev.stylepaper.core.ui.navhost.NavScreen
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.Screen.Discover
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.Screen.Home
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.Screen.Menu
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen(
    @param:DrawableRes val icon: Int,
    @param:StringRes val label: Int,
    @param:StringRes val description: Int
) : NavScreen() {

    @Serializable
    data object WallpapersNavigation : Screen(
        icon = R.drawable.home_icon,
        label = R.string.home_screen_navbar_item_label,
        description = R.string.home_screen_navbar_item_description
    )

    @Serializable
    data object Home : Screen(
        icon = R.drawable.home_icon,
        label = R.string.home_screen_navbar_item_label,
        description = R.string.home_screen_navbar_item_description
    )

    @Serializable
    data object Discover : Screen(
        icon = R.drawable.discover_icon,
        label = R.string.discover_screen_navbar_item_label,
        description = R.string.discover_screen_navbar_item_description
    )

    @Serializable
    data object Search : Screen(
        icon = R.drawable.search_icon,
        label = R.string.search_screen_label,
        description = R.string.search_screen_description
    )

    @Serializable
    data object Menu : Screen(
        icon = R.drawable.menu_icon,
        label = R.string.menu_screen_navbar_item_label,
        description = R.string.menu_screen_navbar_item_description
    )

    @Serializable
    data class WallpaperDetails(val wallpaperId: String) : Screen(
        icon = R.drawable.wallpaper_icon,
        label = R.string.wallpaper_details_screen_label,
        description = R.string.wallpaper_details_screen_description
    )

    @Serializable
    data class ApplyWallpaper(val wallpaperId: String) : Screen(
        icon = R.drawable.wallpaper_icon,
        label = R.string.apply_wallpaper_screen_label,
        description = R.string.apply_wallpaper_screen_description
    )

}

val navDestinations: List<Screen> by lazy { listOf(Home, Discover, Menu) }