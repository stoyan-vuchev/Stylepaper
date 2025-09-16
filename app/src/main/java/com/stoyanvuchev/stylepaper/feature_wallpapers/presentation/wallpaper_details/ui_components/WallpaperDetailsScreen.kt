package com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpaper_details.ui_components

import android.content.Intent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.stoyanvuchev.stylepaper.core.ext.getFileName
import com.stoyanvuchev.stylepaper.core.ext.isScrolledToTheEnd
import com.stoyanvuchev.stylepaper.core.ui.components.SnackbarHostAndScrollButton
import com.stoyanvuchev.stylepaper.core.ui.event.NavigationEvent
import com.stoyanvuchev.stylepaper.core.ui.event.SnackbarEvent
import com.stoyanvuchev.stylepaper.feature_wallpapers.framework.service.WallpaperDownloadService
import com.stoyanvuchev.stylepaper.feature_wallpapers.framework.utils.setWallpaperWithChooser
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpaper_details.WallpaperDetailsScreenUIAction
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpaper_details.WallpaperDetailsScreenViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Composable
fun WallpaperDetailsScreen(
    viewModel: WallpaperDetailsScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val downloadProgress by viewModel.downloadProgress.collectAsStateWithLifecycle()
    val isDownloaded by viewModel.isDownloaded.collectAsStateWithLifecycle(false)
    val context = LocalContext.current

    val lazyListState = rememberLazyListState()
    val snackbarHostState = remember { SnackbarHostState() }

    var firstVisibleItem by rememberSaveable { mutableIntStateOf(0) }
    val isFirstItemVisible by remember {
        derivedStateOf { firstVisibleItem < 1 }
    }

    var isScrollToTopButtonVisible by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemIndex }
            .collect { firstVisibleItem = it }
    }

    var isOverScrollAnimationTriggered by rememberSaveable { mutableStateOf(false) }
    val overScrollAnimation by animateDpAsState(
        targetValue = if (isOverScrollAnimationTriggered) 24.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = ""
    )

    val isDividerVisible by remember {
        derivedStateOf { !lazyListState.isScrolledToTheEnd() }
    }


    LaunchedEffect(isOverScrollAnimationTriggered) {
        delay(256L)
        if (isOverScrollAnimationTriggered) {
            isOverScrollAnimationTriggered = false
        }
    }

    LaunchedEffect(viewModel.actionEvent) {
        viewModel.actionEvent.collectLatest { event ->
            when (event) {

                is WallpaperDetailsScreenUIAction.ScrollToTop -> {
                    isOverScrollAnimationTriggered = true
                    lazyListState.animateScrollToItem(0, 0)
                }

                is WallpaperDetailsScreenUIAction.Download -> {

                    val intent = Intent(
                        context,
                        WallpaperDownloadService::class.java
                    ).apply {
                        putExtra("url", state.wallpaper.path)
                        putExtra("id", state.wallpaper.id)
                        putExtra(
                            "fileName",
                            getFileName(
                                id = state.wallpaper.id,
                                type = state.wallpaper.fileType
                            )
                        )
                    }

                    ContextCompat.startForegroundService(context, intent)

                }

                is WallpaperDetailsScreenUIAction.Apply -> {
                    setWallpaperWithChooser(
                        context = context,
                        id = state.wallpaper.id,
                        fileType = state.wallpaper.fileType
                    )
                }

                else -> Unit

            }
        }
    }

    LaunchedEffect(viewModel.navigationEvent) {
        viewModel.navigationEvent.collectLatest { event ->
            when (event) {
                is NavigationEvent.Back -> navController.navigateUp()
                is NavigationEvent.Navigate -> navController.navigate(event.route)
            }
        }
    }

    LaunchedEffect(viewModel.snackbarEvent) {
        viewModel.snackbarEvent.collect { event ->
            when (event) {
                is SnackbarEvent.Snackbar -> snackbarHostState.showSnackbar(
                    message = event.msg.asString(context)
                )
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        WallpaperDetailsScreenContainer(
            state = state,
            lazyListState = lazyListState,
            overScrollAnimation = overScrollAnimation,
            onUIAction = viewModel::onUIAction
        )

        WallpaperDetailsScreenTopBar(
            modifier = Modifier.align(Alignment.TopCenter),
            isFirstItemVisible = isFirstItemVisible,
            onActionEvent = viewModel::onUIAction
        )

        SnackbarHostAndScrollButton(
            modifier = Modifier
                .padding(bottom = 80.dp)
                .align(Alignment.BottomCenter),
            snackbarHostState = snackbarHostState,
            isScrollToTopButtonVisible = isScrollToTopButtonVisible,
            isScrollInProgress = lazyListState.isScrollInProgress,
            firstVisibleItem = firstVisibleItem,
            onScrollStateChange = remember { { isScrollToTopButtonVisible = it } },
            onScrollToTop = remember {
                { viewModel.onUIAction(WallpaperDetailsScreenUIAction.ScrollToTop) }
            }
        )

        WallpaperDetailsScreenBottomBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            isDividerVisible = isDividerVisible,
            isLoading = state.isLoading,
            isDownloaded = isDownloaded,
            progress = downloadProgress,
            onActionEvent = viewModel::onUIAction
        )

    }

}