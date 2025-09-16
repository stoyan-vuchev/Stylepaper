package com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpaper_details.ui_components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpaper_details.WallpaperDetailsScreenState
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpaper_details.WallpaperDetailsScreenUIAction
import kotlinx.coroutines.delay

@Composable
fun WallpaperDetailsScreenContainer(
    state: WallpaperDetailsScreenState,
    lazyListState: LazyListState,
    overScrollAnimation: Dp,
    onUIAction: (WallpaperDetailsScreenUIAction) -> Unit,
) {

    var isClicked by remember { mutableStateOf(false) }

    LaunchedEffect(isClicked) {
        if (isClicked) {
            delay(500L)
            isClicked = false
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .offset(y = overScrollAnimation),
        state = lazyListState
    ) {

        item(key = 0) { Spacer(modifier = Modifier.height(24.dp)) }

        item(key = 1) {
            Spacer(
                modifier = Modifier
                    .statusBarsPadding()
                    .height(40.dp)
            )
        }

        item(key = 2) {

            WallpaperDetailsScreenWallpaperItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                wallpaper = state.wallpaper,
                onClick = {
                    if (!isClicked) {
                        isClicked = true
                        onUIAction(WallpaperDetailsScreenUIAction.Fullscreen)
                    }
                }
            )

        }

        item(key = 3) {

            Spacer(modifier = Modifier.height(8.dp))

            WallpaperDetailsScreenUploaderItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                uploader = state.wallpaper.uploader,
                createdAt = state.wallpaper.createdAt
            )

        }

        item(key = 4) {

            WallpaperDetailsScreenTagsItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp)
                    .animateContentSize()
                    .animateItem(),
                tags = state.wallpaper.tags
            )

        }

        item(key = 5) {

            Spacer(modifier = Modifier.height(16.dp))

            WallpaperDetailsScreenOtherItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp)
                    .animateItem(),
                resolution = state.wallpaper.resolution,
                size = state.wallpaper.fileSize,
                type = state.wallpaper.fileType,
                aspectRatio = state.wallpaper.aspectRatio,
                shortUrl = state.wallpaper.shortUrl
            )

            Spacer(modifier = Modifier.height(80.dp))

        }

        item(key = 6) {
            Spacer(
                modifier = Modifier
                    .navigationBarsPadding()
                    .height(64.dp)
            )
        }

    }

}