/*
*
* No Copyright 2022 SV100NY
*
* */

package com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core.LoadingIndicator
import com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core.LoadingIndicatorDuration

@Composable
fun LoadingIndicatorWithHorizontalContainer(
    modifier: Modifier = Modifier,
    indicatorSize: Dp = 75.dp,
    indicatorGap: Dp = 16.dp,
    animationSpeed: LoadingIndicatorDuration = LoadingIndicatorDuration.Normal,
    content: @Composable RowScope.() -> Unit
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        LoadingIndicator(
            indicatorSize = indicatorSize,
            duration = animationSpeed
        )

        Spacer(modifier = Modifier.height(indicatorGap))

        content()

    }

}