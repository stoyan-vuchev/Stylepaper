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
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpaperColor.BlueColor
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpaperColor.BrownColor
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpaperColor.CyanColor
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpaperColor.GreenColor
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpaperColor.GreyColor
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpaperColor.None
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpaperColor.OrangeColor
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpaperColor.PinkColor
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpaperColor.PurpleColor
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpaperColor.RedColor
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpaperColor.YellowColor

sealed class WallpaperColor(
    val uiColor: Color,
    val hexColor: String
) {

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

fun String.toWallpaperColor(): WallpaperColor {
    return when (this) {
        "ea4c88" -> PinkColor
        "663399" -> PurpleColor
        "0099CC" -> BlueColor
        "66CCCC" -> CyanColor
        "77CC33" -> GreenColor
        "ffcc33" -> YellowColor
        "ff9900" -> OrangeColor
        "cc3333" -> RedColor
        "996633" -> BrownColor
        "999999" -> GreyColor
        else -> None
    }
}

fun List<String>.toWallpaperColors(): List<WallpaperColor> = this.map { it.toWallpaperColor() }

val colorsList = listOf(
    None,
    PinkColor,
    PurpleColor,
    BlueColor,
    CyanColor,
    GreenColor,
    YellowColor,
    OrangeColor,
    RedColor,
    BrownColor,
    GreyColor
)