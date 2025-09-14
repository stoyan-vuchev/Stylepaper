package com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.home.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.stoyanvuchev.stylepaper.core.utils.ColorProcessingUnit.getBrightestColor
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model.WallpapersListingItemModel

@Composable
fun WallpapersHomeScreenWallpaperItem(
    modifier: Modifier = Modifier,
    wallpaper: WallpapersListingItemModel,
    onClick: () -> Unit
) {

    val color by animateColorAsState(
        targetValue = if (wallpaper.colors.isEmpty()) {
            MaterialTheme.colorScheme.onSurfaceVariant
        } else {
            wallpaper.colors.getBrightestColor()
        }, label = ""
    )

    val image by rememberUpdatedState(
        ImageRequest.Builder(LocalContext.current)
            .data(wallpaper.thumbnails.small)
            .memoryCacheKey(key = wallpaper.thumbnails.small)
            .crossfade(750)
            .build()
    )

    Card(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = 0.5f)
        )
    ) {

        SubcomposeAsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = image,
            contentDescription = "",
            contentScale = ContentScale.Crop
        )

    }

}