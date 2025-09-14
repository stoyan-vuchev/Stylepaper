package com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.home.components

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.stoyanvuchev.stylepaper.core.ui.components.SnackbarHostAndScrollButton
import com.stoyanvuchev.stylepaper.core.ui.event.NavigationEvent
import com.stoyanvuchev.stylepaper.core.ui.event.SnackbarEvent
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpapersHomeSelection
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.home.WallpapersHomeScreenUIAction
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.home.WallpapersHomeScreenViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Composable
fun WallpapersHomeScreen(
    viewModel: WallpapersHomeScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val lazyGridState = rememberLazyGridState()
    val snackbarHostState = remember { SnackbarHostState() }

    var firstVisibleItem by rememberSaveable { mutableIntStateOf(0) }
    val isFirstItemVisible by remember {
        derivedStateOf { firstVisibleItem < 1 }
    }

    var isScrollToTopButtonVisible by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(lazyGridState) {
        snapshotFlow { lazyGridState.firstVisibleItemIndex }
            .collect { firstVisibleItem = it }
    }

    var isReloadingGrid by remember { mutableStateOf(true) }
    val gridAlpha by animateFloatAsState(
        targetValue = if (isReloadingGrid) 0f else 1f,
        label = ""
    )

    LaunchedEffect(isReloadingGrid) {
        if (isReloadingGrid) {
            delay(250L)
            isReloadingGrid = false
        }
    }

    var isOverScrollAnimationTriggered by remember { mutableStateOf(false) }
    val overScrollAnimation by animateDpAsState(
        targetValue = if (isOverScrollAnimationTriggered) 24.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = ""
    )

    LaunchedEffect(isOverScrollAnimationTriggered) {
        delay(256L)
        if (isOverScrollAnimationTriggered) {
            isOverScrollAnimationTriggered = false
        }
    }

    LaunchedEffect(viewModel.actionEvent) {
        viewModel.actionEvent.collectLatest { action ->
            when (action) {

                is WallpapersHomeScreenUIAction.ScrollToTop -> {
                    isScrollToTopButtonVisible = false
                    isOverScrollAnimationTriggered = true
                    lazyGridState.animateScrollToItem(0, 0)
                }

                is WallpapersHomeScreenUIAction.Selection -> isReloadingGrid = true
                else -> Unit

            }
        }
    }

    LaunchedEffect(viewModel.snackbarEvent) {
        viewModel.snackbarEvent.collect { snackbarEvent ->
            when (snackbarEvent) {
                is SnackbarEvent.Snackbar -> {
                    snackbarHostState.showSnackbar(snackbarEvent.msg.asString(context))
                }
            }
        }
    }

    LaunchedEffect(viewModel.navigationEvent) {
        viewModel.navigationEvent.collect { navigationEvent ->
            when (navigationEvent) {
                is NavigationEvent.Navigate -> navController.navigate(navigationEvent.route)
                else -> Unit
            }
        }
    }

    BackHandler(state.selection != WallpapersHomeSelection.Latest) {
        viewModel.onUIAction(WallpapersHomeScreenUIAction.ScrollToTop)
        viewModel.onUIAction(WallpapersHomeScreenUIAction.Selection(WallpapersHomeSelection.Latest))
    }

    Box(modifier = Modifier.fillMaxSize()) {

        WallpapersHomeScreenContainer(
            state = state,
            lazyGridState = lazyGridState,
            isFirstItemVisible = isFirstItemVisible,
            gridAlpha = gridAlpha,
            overScrollAnimation = overScrollAnimation,
            onActionEvent = viewModel::onUIAction
        )

        WallpapersHomeScreenTopBar(
            modifier = Modifier.align(Alignment.TopCenter),
            isFirstItemVisible = isFirstItemVisible,
            isScrollInProgress = lazyGridState.isScrollInProgress,
            selection = state.selection,
            onAction = viewModel::onUIAction
        )

        SnackbarHostAndScrollButton(
            modifier = Modifier
                .padding(bottom = 80.dp)
                .align(Alignment.BottomCenter),
            snackbarHostState = snackbarHostState,
            isScrollToTopButtonVisible = isScrollToTopButtonVisible,
            isScrollInProgress = lazyGridState.isScrollInProgress,
            firstVisibleItem = firstVisibleItem,
            onScrollStateChange = { isScrollToTopButtonVisible = it },
            onScrollToTop = remember {
                { viewModel.onUIAction(WallpapersHomeScreenUIAction.ScrollToTop) }
            }
        )

    }

}