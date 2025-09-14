package com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/** Loading Indicator Constants */
object LoadingIndicatorConstants {

    /** Loading Indicator */

    // Default Loading Indicator Size
    val Default_Loading_Indicator_Size: Dp = 56.dp

    /** Loading Indicator Circles Container */

    // Loading Indicator Container Scale Factor
    const val Circles_Container_Scale_Factor = 0.75f

    // The Initial and Target Value of the Circles Container Rotation Angle
    const val Circles_Container_Rotation_Angle_Initial_Value: Float = 0f
    const val Circles_Container_Rotation_Angle_Target_Value: Float = 360f

    /** Loading Indicator Circles */

    // The Alpha of the Primary and Secondary Circle Colors
    const val Primary_Circle_Color_Alpha = 1f
    const val Secondary_Circle_Color_Alpha = 0.5f

    // The Initial and Target Size Factor of the Circle
    const val Initial_Circle_Size_Factor = 0.25f
    const val Target_Circle_Size_Factor = 0.75f

    // The Initial and Target Offset Factor of the Circle
    const val Initial_Circle_Offset_Factor = 0f
    const val Target_Circle_Offset_Factor = 2.05f

    // Circle Angles
    const val First_Circle_Rotation_Angle = 90f
    const val Second_Circle_Rotation_Angle = 270f
    const val Third_Circle_Rotation_Angle = 0f
    const val Fourth_Circle_Rotation_Angle = 180f

    /** Loading Indicator Horizontal Container */

    // todo

}