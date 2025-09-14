package com.stoyanvuchev.stylepaper.core.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.stylepaper.R

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ScrollButton(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    painter: Painter,
    onClick: () -> Unit
) {

    val containerColor = animateColorAsState(
        targetValue = MaterialTheme.colorScheme.secondary
    ).value

    val contentColor = animateColorAsState(
        targetValue = MaterialTheme.colorScheme.onSecondary
    ).value

    AnimatedVisibility(
        modifier = modifier,
        visible = isVisible,
        enter = fadeIn() + scaleIn(),
        exit = fadeOut() + scaleOut()
    ) {
        Card(
            modifier = Modifier.size(40.dp),
            shape = RoundedCornerShape(100),
            colors = CardDefaults.cardColors(
                containerColor = containerColor,
                contentColor = contentColor
            ),
            onClick = onClick
        ) {
            Icon(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                painter = painter,
                contentDescription = stringResource(id = R.string.scroll_to_top)
            )
        }
    }

}