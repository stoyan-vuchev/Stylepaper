package com.stoyanvuchev.stylepaper.core.ui.components.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SmallTopBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
    shape: RoundedCornerShape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp),
    elevatedColor: Color = MaterialTheme.colorScheme.primary.copy(0.12f)
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background.copy(alpha = 0.97f))
            .then(modifier),
        shape = shape,
        color = elevatedColor
    ) {

        Row(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxWidth()
                .height(64.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )

    }

}