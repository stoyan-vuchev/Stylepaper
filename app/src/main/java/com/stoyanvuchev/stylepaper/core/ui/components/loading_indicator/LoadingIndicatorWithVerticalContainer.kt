/*
*
* No Copyright 2022 SV100NY
*
* */

package com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core.LoadingIndicator
import com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core.LoadingIndicatorDuration

@Composable
fun LoadingIndicatorWithVerticalContainer(
    modifier: Modifier = Modifier,
    indicatorSize: Dp = 75.dp,
    indicatorGap: Dp = 8.dp,
    animationSpeed: LoadingIndicatorDuration = LoadingIndicatorDuration.Normal,
    content: @Composable ColumnScope.() -> Unit
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LoadingIndicator(
            indicatorSize = indicatorSize,
            duration = animationSpeed
        )

        Spacer(modifier = Modifier.height(indicatorGap))

        content()

    }

}