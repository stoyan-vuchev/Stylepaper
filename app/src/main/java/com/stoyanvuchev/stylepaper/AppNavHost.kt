package com.stoyanvuchev.stylepaper

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.stoyanvuchev.stylepaper.core.ui.navhost.NavHostTransitions
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.Screen
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpapersNavGraph

@Composable
fun AppNavHost(
    navController: NavHostController
) {

    val defaultEnterTransition by lazy {
        NavHostTransitions.Enter.scaleAndFadeInWithOvershoot()
    }

    val defaultExitTransition by lazy {
        NavHostTransitions.Exit.scaleAndFadeOutWithOvershoot()
    }

    Box(modifier = Modifier.fillMaxSize()) {

        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = Screen.WallpapersNavigation,
            enterTransition = { defaultEnterTransition },
            exitTransition = { defaultExitTransition }
        ) {

            wallpapersNavGraph(
                navController = navController
            )

        }

        AppNavigationBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            navController = navController
        )

    }

}