package com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpaper_details.ui_components

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.stylepaper.R
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpaper_details.WallpaperDetailsScreenUIAction
import kotlinx.coroutines.delay

@Composable
fun WallpaperDetailsScreenBottomBar(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    isDownloaded: Boolean,
    isDividerVisible: Boolean,
    progress: Int,
    onActionEvent: (WallpaperDetailsScreenUIAction) -> Unit
) {

    var isClicked by remember { mutableStateOf(false) }

    LaunchedEffect(isClicked) {
        if (isClicked) {
            delay(500L)
            isClicked = false
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background.copy(alpha = 0.97f))
            .then(modifier),
        shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0f)
        )
    ) {

        androidx.compose.animation.AnimatedVisibility(
            visible = isDividerVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            HorizontalDivider(modifier = Modifier.padding(horizontal = 14.dp))
        }

        Box(
            modifier = Modifier
                .navigationBarsPadding()
                .fillMaxWidth()
                .height(80.dp),
            contentAlignment = Alignment.Center
        ) {

            androidx.compose.animation.AnimatedVisibility(
                visible = progress == 0 || progress == 100,
                enter = remember { fadeIn() + slideInVertically { it } },
                exit = remember { slideOutVertically { it } + fadeOut() },
            ) {

                Button(
                    onClick = {
                        if (!isClicked && !isLoading) {
                            isClicked = true
                            onActionEvent(
                                if (isDownloaded) WallpaperDetailsScreenUIAction.Apply
                                else WallpaperDetailsScreenUIAction.Download
                            )
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ) {

                    Icon(
                        painter = painterResource(
                            id = if (isDownloaded) R.drawable.wallpaper_icon
                            else R.drawable.download_icon
                        ),
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = stringResource(
                            id = if (isDownloaded) R.string.apply_wallpaper
                            else R.string.download_wallpaper
                        ),
                        style = MaterialTheme.typography.titleMedium
                    )

                }

            }

            androidx.compose.animation.AnimatedVisibility(
                visible = progress > 0 && progress < 100,
                enter = remember { fadeIn() },
                exit = remember { fadeOut() },
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(horizontal = 32.dp),
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "Downloading: $progress%",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    val progressFloat by remember(progress) {
                        derivedStateOf { progress.toFloat() / 100 }
                    }

                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth(),
                        progress = { progressFloat }
                    )

                }

            }

        }

    }

}