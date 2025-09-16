package com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpaper_details.ui_components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.stoyanvuchev.stylepaper.R
import com.stoyanvuchev.stylepaper.core.utils.DateTimeUtils.toReadableDateTimeFormat
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model.WallpaperUploaderModel

@Composable
fun WallpaperDetailsScreenUploaderItem(
    modifier: Modifier = Modifier,
    uploader: WallpaperUploaderModel,
    createdAt: String
) {

    var isImageLoaded by remember { mutableStateOf(false) }
    val imageUrl by rememberUpdatedState(uploader.avatar.medium)
    val imagePlaceholder by rememberUpdatedState(
        painterResource(
            id = if (uploader.group == "deleted") R.drawable.question
            else R.drawable.person
        )
    )

    val username by rememberUpdatedState(
        if (uploader.group == "deleted") {
            stringResource(id = R.string.unknown_uploader)
        } else uploader.username
    )

    val timestamp by rememberUpdatedState(
        if (createdAt.isNotBlank()) {
            createdAt.toReadableDateTimeFormat()
        } else ""
    )

    val borderColor by animateColorAsState(
        targetValue = if (!isImageLoaded) {
            MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0f)
        } else {
            if (uploader.group == "deleted") {
                MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0f)
            } else MaterialTheme.colorScheme.outlineVariant
        }
    )

    val image = ImageRequest.Builder(LocalContext.current)
        .data(imageUrl)
        .crossfade(750)
        .memoryCacheKey(uploader.avatar.medium)
        .build()

    AnimatedVisibility(
        modifier = modifier.animateContentSize(),
        visible = uploader != WallpaperUploaderModel(),
        enter = fadeIn(
            animationSpec = tween(durationMillis = 768)
        ) + slideInHorizontally { -it / 2 },
        exit = ExitTransition.None
    ) {

        Column(modifier = Modifier.fillMaxWidth()) {

            Text(
                text = stringResource(id = R.string.uploaded_by),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Card(
                    modifier = Modifier.size(48.dp),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(
                        width = 1.dp,
                        color = borderColor
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.64f)
                    )
                ) {

                    if (imageUrl.isNotBlank()) {

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

                                isImageLoaded = false

                                Box {

                                    Icon(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .align(Alignment.Center),
                                        painter = imagePlaceholder,
                                        contentDescription = "",
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                                    )

                                }

                            } else {

                                isImageLoaded = true

                                SubcomposeAsyncImageContent()

                            }
                        }

                    } else {

                        Box(modifier = Modifier.fillMaxSize()) {

                            Icon(
                                modifier = Modifier
                                    .size(32.dp)
                                    .align(Alignment.Center),
                                painter = imagePlaceholder,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                        }

                    }

                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {

                    Text(
                        text = username,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = timestamp,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                }

            }

        }

    }

}