package com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpaper_details.ui_components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import com.stoyanvuchev.stylepaper.R

@Composable
fun WallpaperDetailsScreenOtherItem(
    modifier: Modifier = Modifier,
    resolution: String,
    size: String,
    aspectRatio: String,
    type: String,
    shortUrl: String,
) {

    val uriHandler = LocalUriHandler.current
    var isClicked by remember { mutableStateOf(false) }

    val resolutionText by rememberUpdatedState(
        "${stringResource(id = R.string.resolution)}:  $resolution"
    )

    val aspectRatioText by rememberUpdatedState(
        "${stringResource(id = R.string.aspect_ratio)}:  $aspectRatio"
    )

    val sizeText by rememberUpdatedState("${stringResource(id = R.string.image_size)}:  $size")
    val typeText by rememberUpdatedState("${stringResource(id = R.string.image_type)}:  $type")

    LaunchedEffect(isClicked) {
        if (isClicked) {
            delay(500L)
            isClicked = false
        }
    }

    Column(modifier = modifier.animateContentSize()) {

        Text(
            text = stringResource(id = R.string.other),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.outline
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = resolutionText,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = aspectRatioText,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = sizeText,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = typeText,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(40.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            TextButton(
                modifier = Modifier.align(Alignment.Center),
                onClick = {
                    if (!isClicked) {
                        isClicked = true
                        uriHandler.openUri(shortUrl)
                    }
                }
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = stringResource(id = R.string.view_on_wallhaven),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

    }

}