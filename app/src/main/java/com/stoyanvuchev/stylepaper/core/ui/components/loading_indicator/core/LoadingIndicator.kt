package com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp
import com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core.LoadingIndicatorConstants.Circles_Container_Rotation_Angle_Initial_Value
import com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core.LoadingIndicatorConstants.Circles_Container_Rotation_Angle_Target_Value
import com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core.LoadingIndicatorConstants.Default_Loading_Indicator_Size
import com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core.LoadingIndicatorConstants.First_Circle_Rotation_Angle
import com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core.LoadingIndicatorConstants.Fourth_Circle_Rotation_Angle
import com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core.LoadingIndicatorConstants.Initial_Circle_Offset_Factor
import com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core.LoadingIndicatorConstants.Initial_Circle_Size_Factor
import com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core.LoadingIndicatorConstants.Second_Circle_Rotation_Angle
import com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core.LoadingIndicatorConstants.Target_Circle_Size_Factor
import com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core.LoadingIndicatorConstants.Third_Circle_Rotation_Angle
import com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core.LoadingIndicatorUtils.containerSize
import com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core.LoadingIndicatorUtils.loadingIndicatorPrimaryColor
import com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core.LoadingIndicatorUtils.loadingIndicatorSecondaryColor
import com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core.LoadingIndicatorUtils.targetCircleOffset

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    indicatorSize: Dp = Default_Loading_Indicator_Size,
    duration: LoadingIndicatorDuration = LoadingIndicatorDuration.Normal,
    primaryColor: Color = loadingIndicatorPrimaryColor(),
    secondaryColor: Color = loadingIndicatorSecondaryColor()
) {

    // The size of the Loading Indicator Container
    val indicatorContainerSize = remember { indicatorSize.containerSize() }

    // The Rotation Angle of the Loading Indicator Container
    val initialCirclesRotationAngle = remember { Circles_Container_Rotation_Angle_Initial_Value }
    val targetCirclesRotationAngle = remember { Circles_Container_Rotation_Angle_Target_Value }

    // The Size of the Loading Indicator circles
    val initialCircleSize = remember { indicatorSize.value * Initial_Circle_Size_Factor }
    val targetCircleSize = remember { indicatorSize.value * Target_Circle_Size_Factor }

    // The Offset of the Loading Indicator circles
    val initialCircleOffset = remember { Initial_Circle_Offset_Factor }
    val targetCircleOffset = remember {
        indicatorContainerSize.targetCircleOffset(initialCircleSize, targetCircleSize)
    }

    // Infinite transition for the Animations
    val infiniteTransition = rememberInfiniteTransition()

    // The Rotation Angle of the circles
    val circlesRotationAngle by infiniteTransition.animateValue(
        initialValue = initialCirclesRotationAngle,
        targetValue = targetCirclesRotationAngle,
        typeConverter = Float.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = duration.millis
                initialCirclesRotationAngle at duration.start
                targetCirclesRotationAngle at duration.end
            }
        )
    )

    // The Scale of the Circle
    val circleScale by infiniteTransition.animateValue(
        initialValue = initialCircleSize,
        targetValue = targetCircleSize,
        typeConverter = Float.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = duration.millis
                initialCircleSize at duration.start
                targetCircleSize at duration.halfWay
                initialCircleSize at duration.end
            }
        )
    )

    // The Offset of the circle
    val circleOffset by infiniteTransition.animateValue(
        initialValue = initialCircleOffset,
        targetValue = targetCircleOffset,
        typeConverter = Float.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = duration.millis
                initialCircleOffset at duration.start
                targetCircleOffset at duration.halfWay
                initialCircleOffset at duration.end
            }
        )
    )

    // Loading Indicator
    Box(
        modifier = Modifier
            .size(indicatorSize)
            .then(modifier)
    ) {

        // Loading Indicator container
        Canvas(
            modifier = Modifier
                .size(indicatorContainerSize)
                .align(Alignment.Center),
            onDraw = {

                // Applying the Rotation Angle
                rotate(degrees = circlesRotationAngle) {

                    // First circle
                    rotate(First_Circle_Rotation_Angle) {
                        drawCircle(
                            color = secondaryColor,
                            radius = circleScale,
                            center = Offset(x = circleOffset, y = circleOffset)
                        )
                    }

                    // Second circle
                    rotate(Second_Circle_Rotation_Angle) {
                        drawCircle(
                            color = secondaryColor,
                            radius = circleScale,
                            center = Offset(x = circleOffset, y = circleOffset)
                        )
                    }

                    // Third circle
                    rotate(Third_Circle_Rotation_Angle) {
                        drawCircle(
                            color = primaryColor,
                            radius = circleScale,
                            center = Offset(x = circleOffset, y = circleOffset)
                        )
                    }

                    // Fourth circle
                    rotate(Fourth_Circle_Rotation_Angle) {
                        drawCircle(
                            color = primaryColor,
                            radius = circleScale,
                            center = Offset(x = circleOffset, y = circleOffset)
                        )
                    }

                }

            }
        )

    }

}