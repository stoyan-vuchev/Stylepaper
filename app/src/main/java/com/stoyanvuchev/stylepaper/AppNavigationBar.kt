package com.stoyanvuchev.stylepaper

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.Screen
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpapersNavBarDestinations

@Composable
fun AppNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    val destinations = rememberSaveable { wallpapersNavBarDestinations }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination by rememberUpdatedState(
        navBackStackEntry?.destination
    )

    val isNavBarVisible by remember(currentDestination) {
        derivedStateOf {
            destinations.any { destination ->
                currentDestination?.hasRoute(destination::class) ?: false
            }
        }
    }

    AnimatedVisibility(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        visible = isNavBarVisible,
        enter = remember { fadeIn() + slideInVertically { it } },
        exit = remember { slideOutVertically { it } + fadeOut() }
    ) {

        NavigationBar(modifier = Modifier.fillMaxWidth()) {

            destinations.forEach { screen ->

                val selected by remember(currentDestination) {
                    derivedStateOf {
                        currentDestination?.hasRoute(screen::class) ?: false
                    }
                }

                NavigationBarItem(
                    label = { Text(text = stringResource(id = screen.label)) },
                    icon = {

                        Icon(
                            painter = painterResource(id = screen.icon),
                            contentDescription = stringResource(id = screen.description),
                        )

                    },
                    selected = selected,
                    onClick = remember {
                        {

                            navController.navigate(screen) {

                                popUpTo(Screen.Home) {
                                    this.saveState = true
                                    this.inclusive = false
                                }

                                launchSingleTop = true
                                restoreState = true

                            }

                        }
                    }
                )

            }

        }

    }

}