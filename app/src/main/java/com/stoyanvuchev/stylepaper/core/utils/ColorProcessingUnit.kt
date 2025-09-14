package com.stoyanvuchev.stylepaper.core.utils

import androidx.annotation.ColorInt
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.util.fastMap
import androidx.core.graphics.ColorUtils
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpaperColor
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.toUIColor
import kotlin.math.sqrt

object ColorProcessingUnit {

    fun List<WallpaperColor>.getBrightestColor(): Color {
        return this.map { it.toUIColor() }
            .maxByOrNull { it.luminance() } ?: WallpaperColor.None().toUIColor()
    }

    private fun Color.luminance(): Float {

        val r: Double = this.red.toDouble()
        val g: Double = this.green.toDouble()
        val b: Double = this.blue.toDouble()

        return sqrt((0.299 * r) + (0.587 * g) + (0.114 * b)).toFloat()

    }

    // fun String.hexToColor(): Color = Color(android.graphics.Color.parseColor(this))

    fun Color.inverse(ratio: Float = 0f): Color {

        val tonalColor = if (this.luminance() >= 0.75f) {
            android.graphics.Color.BLACK
        } else {
            android.graphics.Color.WHITE
        }

        return if (ratio > 0f) {
            inverseColor(this, tonalColor, ratio)
        } else {
            if (this.luminance() >= 0.9f) {
                this.darken(0.16f)
            } else {
                this.lighten(0.75f)
            }
        }

    }

    fun Color.darken(ratio: Float = 0.5f): Color {
        return inverseColor(
            color = this,
            tonalColor = android.graphics.Color.BLACK,
            ratio = ratio
        )
    }

    fun Color.lighten(ratio: Float = 0.5f): Color {
        return inverseColor(
            color = this,
            tonalColor = android.graphics.Color.WHITE,
            ratio = ratio
        )
    }

    private fun inverseColor(
        color: Color,
        @ColorInt tonalColor: Int,
        ratio: Float
    ): Color = Color(
        ColorUtils.blendARGB(
            color.value.toInt(),
            tonalColor,
            ratio
        )
    )

}