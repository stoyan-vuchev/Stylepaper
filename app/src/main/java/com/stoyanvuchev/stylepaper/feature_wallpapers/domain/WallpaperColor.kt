package com.stoyanvuchev.stylepaper.feature_wallpapers.domain

import androidx.compose.ui.graphics.Color
import com.stoyanvuchev.stylepaper.core.ui.theme.Blue
import com.stoyanvuchev.stylepaper.core.ui.theme.Brown
import com.stoyanvuchev.stylepaper.core.ui.theme.Cyan
import com.stoyanvuchev.stylepaper.core.ui.theme.Green
import com.stoyanvuchev.stylepaper.core.ui.theme.Grey
import com.stoyanvuchev.stylepaper.core.ui.theme.Orange
import com.stoyanvuchev.stylepaper.core.ui.theme.Pink
import com.stoyanvuchev.stylepaper.core.ui.theme.Purple
import com.stoyanvuchev.stylepaper.core.ui.theme.Red
import com.stoyanvuchev.stylepaper.core.ui.theme.Yellow

sealed class WallpaperColor(
    val uiColor: Color,
    val hexColor: String
) {

    companion object Companion {
        val colorsList = listOf(
            None, PinkColor, PurpleColor, BlueColor, CyanColor, GreenColor,
            YellowColor, OrangeColor, RedColor, BrownColor, GreyColor
        )
    }

    object None : WallpaperColor(
        uiColor = Color(0x00000000),
        hexColor = ""
    )

    object PinkColor : WallpaperColor(
        uiColor = Pink,
        hexColor = "ea4c88"
    )

    object PurpleColor : WallpaperColor(
        uiColor = Purple,
        hexColor = "663399"
    )

    object BlueColor : WallpaperColor(
        uiColor = Blue,
        hexColor = "0099CC"
    )

    object CyanColor : WallpaperColor(
        uiColor = Cyan,
        hexColor = "66CCCC"
    )

    object GreenColor : WallpaperColor(
        uiColor = Green,
        hexColor = "77CC33"
    )

    object YellowColor : WallpaperColor(
        uiColor = Yellow,
        hexColor = "ffcc33"
    )

    object OrangeColor : WallpaperColor(
        uiColor = Orange,
        hexColor = "ff9900"
    )

    object RedColor : WallpaperColor(
        uiColor = Red,
        hexColor = "cc3333"
    )

    object BrownColor : WallpaperColor(
        uiColor = Brown,
        hexColor = "996633"
    )

    object GreyColor : WallpaperColor(
        uiColor = Grey,
        hexColor = "999999"
    )

}