package com.stoyanvuchev.stylepaper

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.stoyanvuchev.stylepaper.core.ui.navhost.NavHostTransitions
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.Screen
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpapersNavGraph

@Composable
fun AppNavHost(
    navController: NavHostController
) {

    val layoutDirection = LocalLayoutDirection.current

    Box(modifier = Modifier.fillMaxSize()) {

        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = Screen.WallpapersNavigation,
            enterTransition = remember {
                { NavHostTransitions.Enter.scaleAndFadeInWithOvershoot() }
            },
            exitTransition = remember {
                { NavHostTransitions.Exit.scaleAndFadeOutWithOvershoot() }
            }
        ) {

            wallpapersNavGraph(
                navController = navController,
                layoutDirection = layoutDirection
            )

        }

        AppNavigationBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            navController = navController
        )

    }

}