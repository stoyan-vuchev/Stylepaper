package com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.stylepaper.R
import com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core.LoadingIndicator
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.home.WallpapersHomeScreenState
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.home.WallpapersHomeScreenUIAction

@Composable
fun WallpapersHomeScreenContainer(
    state: WallpapersHomeScreenState,
    lazyGridState: LazyGridState,
    isFirstItemVisible: Boolean,
    gridAlpha: Float,
    overScrollAnimation: Dp,
    onActionEvent: (WallpapersHomeScreenUIAction) -> Unit
) {

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        state = lazyGridState,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {

        item(span = { GridItemSpan(2) }) {
            Spacer(modifier = Modifier.height(38.dp))
        }

        item(span = { GridItemSpan(2) }) {
            Spacer(
                modifier = Modifier
                    .statusBarsPadding()
                    .height(38.dp)
            )
        }

        item(span = { GridItemSpan(2) }) {

            Column {

                WallpapersHomeScreenSelectionItem(
                    currentSelection = state.selection,
                    isFirstItemVisible = isFirstItemVisible,
                    onAction = onActionEvent
                )

                Spacer(modifier = Modifier.height(24.dp))

            }

        }

        item(span = { GridItemSpan(2) }) {
            Spacer(
                modifier = Modifier
                    .height(overScrollAnimation)
                    .animateContentSize()
            )
        }

        items(
            items = state.wallpapersListing.wallpapers,
            key = { item -> "item_${item.id}" }
        ) { wallpaper ->

            WallpapersHomeScreenWallpaperItem(
                modifier = Modifier
                    .graphicsLayer { this.alpha = gridAlpha }
                    .fillMaxSize()
                    .aspectRatio(1f)
                    .padding(8.dp),
                wallpaper = wallpaper,
                onClick = {
                    onActionEvent(
                        WallpapersHomeScreenUIAction.ViewWallpaper(wallpaper.id)
                    )
                }
            )

        }

        item(span = { GridItemSpan(2) }) {

            LaunchedEffect(Unit) {
                if (
                    !state.endReached
                    && !state.isLoading
                    && state.error == null
                ) {
                    onActionEvent(WallpapersHomeScreenUIAction.LoadNextItems)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 150.dp),
                contentAlignment = Alignment.Center
            ) {

                AnimatedVisibility(
                    visible = state.isLoading,
                    enter = fadeIn() + scaleIn(),
                    exit = scaleOut() + fadeOut()
                ) {
                    LoadingIndicator(indicatorSize = 48.dp)
                }

                AnimatedVisibility(
                    visible = state.error != null && !state.isLoading,
                    enter = fadeIn() + scaleIn(),
                    exit = scaleOut() + fadeOut()
                ) {

                    Text(
                        modifier = Modifier.padding(24.dp),
                        text = state.error?.asString() ?: "",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error
                    )

                }

                AnimatedVisibility(
                    visible = state.endReached && !state.isLoading,
                    enter = fadeIn() + scaleIn(),
                    exit = scaleOut() + fadeOut()
                ) {
                    Text(
                        modifier = Modifier.padding(24.dp),
                        text = stringResource(id = R.string.end_reached),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.75f)
                    )
                }

            }

            Spacer(modifier = Modifier.height(330.dp))
            Spacer(modifier = Modifier.navigationBarsPadding())
        }

    }

}