package com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core.LoadingIndicatorConstants.Circles_Container_Scale_Factor
import com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core.LoadingIndicatorConstants.Primary_Circle_Color_Alpha
import com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core.LoadingIndicatorConstants.Secondary_Circle_Color_Alpha
import com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core.LoadingIndicatorConstants.Target_Circle_Offset_Factor

/** Loading Indicator Utils */
object LoadingIndicatorUtils {

    /** Loading Indicator Container Size */

    fun Dp.containerSize(): Dp = (this.value * Circles_Container_Scale_Factor).dp

    /** Loading Indicator Colors */

    @Composable
    fun loadingIndicatorPrimaryColor(): Color {
        return MaterialTheme.colorScheme.primary.copy(
            alpha = Primary_Circle_Color_Alpha
        )
    }

    @Composable
    fun loadingIndicatorSecondaryColor(): Color {
        return MaterialTheme.colorScheme.secondary.copy(
            alpha = Secondary_Circle_Color_Alpha
        )
    }

    /** Loading Indicator Target Circle Offset */

    fun Dp.targetCircleOffset(initialCircleSize: Float, targetCircleSize: Float): Float {
        return (targetCircleSize - initialCircleSize) * Target_Circle_Offset_Factor
    }

}