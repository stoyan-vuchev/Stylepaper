package com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpaper_details.ui_components

import androidx.compose.animation.ExitTransition
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.stoyanvuchev.stylepaper.R
import com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core.LoadingIndicator
import com.stoyanvuchev.stylepaper.core.utils.NumberUtils.getFormattedNumber
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model.WallpaperModel

@Composable
fun WallpaperDetailsScreenWallpaperItem(
    modifier: Modifier = Modifier,
    wallpaper: WallpaperModel,
    onClick: () -> Unit
) {

    var isWallpaperLoaded by rememberSaveable { mutableStateOf(false) }

    val borderColor = animateColorAsState(
        targetValue = if (!isWallpaperLoaded) {
            MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0f)
        } else {
            MaterialTheme.colorScheme.outlineVariant
        }
    ).value

    val views by remember(wallpaper) {
        derivedStateOf { wallpaper.views.getFormattedNumber() }
    }

    val image = ImageRequest.Builder(LocalContext.current)
        .data(wallpaper.path)
        .memoryCacheKey(key = wallpaper.path)
        .crossfade(750)
        .build()

    Card(
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(24.dp)),
        onClick = { if (isWallpaperLoaded) onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ),
        border = BorderStroke(
            width = 1.dp,
            color = borderColor
        )
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            SubcomposeAsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = image,
                contentDescription = "",
                contentScale = ContentScale.Crop
            ) {

                val imageState = painter.state

                if (
                    imageState is AsyncImagePainter.State.Loading
                    || imageState is AsyncImagePainter.State.Error
                ) {

                    isWallpaperLoaded = false

                    Box(modifier = Modifier.fillMaxSize()) {
                        LoadingIndicator(modifier = Modifier.align(Center))
                    }

                } else {

                    isWallpaperLoaded = true

                    SubcomposeAsyncImageContent()

                }

            }

            androidx.compose.animation.AnimatedVisibility(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(12.dp),
                visible = isWallpaperLoaded,
                enter = fadeIn(
                    animationSpec = tween(durationMillis = 768)
                ) + slideInHorizontally { it * 2 },
                exit = ExitTransition.None
            ) {

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.background
                            .copy(alpha = 0.75f)
                    ),
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(
                        width = 1.dp,
                        color = borderColor
                    )
                ) {

                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = CenterVertically
                    ) {

                        Spacer(modifier = Modifier.width(6.dp))

                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.visibility_on),
                            contentDescription = stringResource(id = R.string.views),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = views,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                    }

                }

            }

        }

    }

}