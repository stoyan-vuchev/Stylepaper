package com.stoyanvuchev.stylepaper.feature_wallpapers.domain

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
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
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class WallpaperColor(
    val hexColor: String
) : Parcelable {

    @Parcelize
    data class None(val hex: String = "#999999") : Parcelable, WallpaperColor(
        hexColor = "999999"
    )

    object PinkColor : WallpaperColor(
        hexColor = "ea4c88"
    )

    object PurpleColor : WallpaperColor(
        hexColor = "663399"
    )

    object BlueColor : WallpaperColor(
        hexColor = "0099CC"
    )

    object CyanColor : WallpaperColor(
        hexColor = "66CCCC"
    )

    object GreenColor : WallpaperColor(
        hexColor = "77CC33"
    )

    object YellowColor : WallpaperColor(
        hexColor = "ffcc33"
    )

    object OrangeColor : WallpaperColor(
        hexColor = "ff9900"
    )

    object RedColor : WallpaperColor(
        hexColor = "cc3333"
    )

    object BrownColor : WallpaperColor(
        hexColor = "996633"
    )

    object GreyColor : WallpaperColor(
        hexColor = "999999"
    )

}

fun WallpaperColor.toUIColor(): Color {
    return when (this) {
        is PinkColor -> Pink
        is PurpleColor -> Purple
        is BlueColor -> Blue
        is CyanColor -> Cyan
        is GreenColor -> Green
        is YellowColor -> Yellow
        is OrangeColor -> Orange
        is RedColor -> Red
        is BrownColor -> Brown
        is GreyColor -> Grey
        else -> Grey
    }
}

fun String.toWallpaperColor(): WallpaperColor {
    return when (this) {
        "#ea4c88" -> PinkColor
        "#663399" -> PurpleColor
        "#0099cc" -> BlueColor
        "#66cccc" -> CyanColor
        "#77cc33" -> GreenColor
        "#ffcc33" -> YellowColor
        "#cc9900" -> OrangeColor
        "#cc3333" -> RedColor
        "#996633" -> BrownColor
        "#999999" -> GreyColor
        else -> None(this.toColorInt().toHexString())
    }
}

fun List<String>.toWallpaperColors(): List<WallpaperColor> = this.map { it.toWallpaperColor() }

val colorsList = listOf(
    None(),
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