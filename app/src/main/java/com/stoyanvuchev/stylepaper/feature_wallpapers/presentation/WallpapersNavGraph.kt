package com.stoyanvuchev.stylepaper.feature_wallpapers.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.stoyanvuchev.stylepaper.core.ui.navhost.safeScreen
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.home.components.WallpapersHomeScreen

fun NavGraphBuilder.wallpapersNavGraph(
    navController: NavHostController
) {

    val homeScreenTransitions by lazy { WallpapersNavGraphTransitions.Home }
    val discoverScreenTransitions by lazy { WallpapersNavGraphTransitions.Discover }
    val menuScreenTransitions by lazy { WallpapersNavGraphTransitions.Menu }
    val wallpaperDetailsTransitions by lazy { WallpapersNavGraphTransitions.WallpaperDetails }

    navigation<Screen.WallpapersNavigation>(
        startDestination = Screen.Home
    ) {

        composable<Screen.Home>(
            enterTransition = {
                homeScreenTransitions.enterTransition(
                    scope = this,
                    initialRoute = initialState.safeScreen(),
                    targetRoute = targetState.safeScreen()
                )
            },
            exitTransition = {
                homeScreenTransitions.exitTransition(
                    scope = this,
                    initialRoute = initialState.safeScreen(),
                    targetRoute = targetState.safeScreen()
                )
            }
        ) {

            WallpapersHomeScreen(
                navController = navController
            )

        }

        composable<Screen.Discover>(
            enterTransition = {
                discoverScreenTransitions.enterTransition(
                    scope = this,
                    initialRoute = initialState.safeScreen()
                )
            },
            exitTransition = {
                discoverScreenTransitions.exitTransition(
                    scope = this,
                    targetRoute = targetState.safeScreen()
                )
            }
        ) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "Discover Screen",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Start
                )

            }

        }

        composable<Screen.Search> {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "Search Screen",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Start
                )

            }

        }

        composable<Screen.Menu>(
            enterTransition = {
                menuScreenTransitions.enterTransition(scope = this)
            },
            exitTransition = {
                menuScreenTransitions.exitTransition(scope = this)
            }
        ) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "Menu Screen",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Start
                )

            }

        }

        composable<Screen.WallpaperDetails>(
            enterTransition = {
                wallpaperDetailsTransitions.enterTransition(
                    initialRoute = initialState.toRoute<Screen>()
                )
            },
            exitTransition = {
                wallpaperDetailsTransitions.exitTransition(
                    targetState = targetState.toRoute<Screen>()
                )
            }
        ) {

            // todo

        }

        composable<Screen.ApplyWallpaper> {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "Apply Wallpaper Screen",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Start
                )

            }

        }

    }

}