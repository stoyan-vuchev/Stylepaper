package com.stoyanvuchev.stylepaper.feature_wallpapers.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.stoyanvuchev.stylepaper.core.ui.navhost.safeScreen
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.home.components.WallpapersHomeScreen
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpaper_details.ui_components.WallpaperDetailsScreen

fun NavGraphBuilder.wallpapersNavGraph(
    navController: NavHostController,
    layoutDirection: LayoutDirection
) {

    navigation<Screen.WallpapersNavigation>(
        startDestination = Screen.Home
    ) {

        composable<Screen.Home>(
            enterTransition = {
                WallpapersNavGraphTransitions.Home.enterTransition(
                    scope = this,
                    initialRoute = initialState.safeScreen(),
                    targetRoute = targetState.safeScreen(),
                    layoutDirection = layoutDirection
                )
            },
            exitTransition = {
                WallpapersNavGraphTransitions.Home.exitTransition(
                    scope = this,
                    initialRoute = initialState.safeScreen(),
                    targetRoute = targetState.safeScreen(),
                    layoutDirection = layoutDirection
                )
            }
        ) {

            WallpapersHomeScreen(
                navController = navController
            )

        }

        composable<Screen.Discover>(
            enterTransition = {
                WallpapersNavGraphTransitions.Discover.enterTransition(
                    scope = this,
                    initialRoute = initialState.safeScreen(),
                    layoutDirection
                )
            },
            exitTransition = {
                WallpapersNavGraphTransitions.Discover.exitTransition(
                    scope = this,
                    targetRoute = targetState.safeScreen(),
                    layoutDirection
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
                WallpapersNavGraphTransitions.Menu.enterTransition(
                    scope = this,
                    layoutDirection = layoutDirection
                )
            },
            exitTransition = {
                WallpapersNavGraphTransitions.Menu.exitTransition(
                    scope = this,
                    layoutDirection = layoutDirection
                )
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
                WallpapersNavGraphTransitions.WallpaperDetails.enterTransition(
                    initialRoute = initialState.safeScreen()
                )
            },
            exitTransition = {
                WallpapersNavGraphTransitions.WallpaperDetails.exitTransition(
                    targetState = targetState.safeScreen()
                )
            }
        ) {

            WallpaperDetailsScreen(
                navController = navController
            )

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