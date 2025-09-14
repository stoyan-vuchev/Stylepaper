package com.stoyanvuchev.stylepaper

import androidx.compose.runtime.Composable
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

    NavHost(
        navController = navController,
        startDestination = Screen.WallpapersNavigation,
        enterTransition = { defaultEnterTransition },
        exitTransition = { defaultExitTransition }
    ) {

        wallpapersNavGraph(
            navController = navController
        )

    }

}