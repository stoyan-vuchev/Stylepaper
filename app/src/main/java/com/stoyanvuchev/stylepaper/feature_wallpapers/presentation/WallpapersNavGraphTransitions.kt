package com.stoyanvuchev.stylepaper.feature_wallpapers.presentation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavBackStackEntry
import com.stoyanvuchev.stylepaper.core.ui.navhost.NavHostTransitions

sealed interface WallpapersNavGraphTransitions {

    data object Home : WallpapersNavGraphTransitions {

        fun enterTransition(
            scope: AnimatedContentTransitionScope<NavBackStackEntry>,
            initialRoute: Screen?,
            targetRoute: Screen?
        ) = when {

            targetRoute is Screen.Discover
                    || initialRoute is Screen.Discover
                    && targetRoute is Screen.Home -> {

                NavHostTransitions.Enter.slideInRightIntoContainerAndFadeIn(
                    scope = scope,
                    towards = AnimatedContentTransitionScope.SlideDirection.Right
                )

            }

            targetRoute is Screen.Menu
                    || initialRoute is Screen.Menu
                    && targetRoute is Screen.Home -> {

                NavHostTransitions.Enter.slideInRightIntoContainerAndFadeIn(
                    scope = scope,
                    towards = AnimatedContentTransitionScope.SlideDirection.Right
                )

            }

            else -> NavHostTransitions.Enter.scaleAndFadeInWithOvershoot(initialScale = 1.1f)

        }

        fun exitTransition(
            scope: AnimatedContentTransitionScope<NavBackStackEntry>,
            initialRoute: Screen?,
            targetRoute: Screen?
        ) = when {

            initialRoute is Screen.Home
                    && targetRoute is Screen.Discover
                    || initialRoute is Screen.Discover
                    && targetRoute is Screen.Home -> {

                NavHostTransitions.Exit.slideOutOfContainerAndFadeOut(
                    scope = scope,
                    towards = AnimatedContentTransitionScope.SlideDirection.Left
                )

            }

            initialRoute is Screen.Home
                    && targetRoute is Screen.Menu
                    || initialRoute is Screen.Menu
                    && targetRoute is Screen.Home -> {

                NavHostTransitions.Exit.slideOutOfContainerAndFadeOut(
                    scope = scope,
                    towards = AnimatedContentTransitionScope.SlideDirection.Left
                )

            }

            else -> NavHostTransitions.Exit.scaleAndFadeOutWithOvershoot(targetScale = 1.1f)

        }

    }

    data object Discover : WallpapersNavGraphTransitions {

        fun enterTransition(
            scope: AnimatedContentTransitionScope<NavBackStackEntry>,
            initialRoute: Screen?
        ) = when (initialRoute) {

            is Screen.Home -> NavHostTransitions.Enter
                .slideInRightIntoContainerAndFadeIn(
                    scope = scope,
                    towards = AnimatedContentTransitionScope.SlideDirection.Left
                )

            is Screen.Menu -> NavHostTransitions.Enter
                .slideInRightIntoContainerAndFadeIn(
                    scope = scope,
                    towards = AnimatedContentTransitionScope.SlideDirection.Right
                )

            else -> NavHostTransitions.Enter.scaleAndFadeInWithOvershoot(initialScale = 1.1f)

        }

        fun exitTransition(
            scope: AnimatedContentTransitionScope<NavBackStackEntry>,
            targetRoute: Screen?
        ) = when (targetRoute) {

            is Screen.Home -> NavHostTransitions.Exit
                .slideOutOfContainerAndFadeOut(
                    scope = scope,
                    towards = AnimatedContentTransitionScope.SlideDirection.Right
                )

            is Screen.Menu -> NavHostTransitions.Exit
                .slideOutOfContainerAndFadeOut(
                    scope = scope,
                    towards = AnimatedContentTransitionScope.SlideDirection.Left
                )

            else -> NavHostTransitions.Exit.scaleAndFadeOutWithOvershoot(targetScale = 1.1f)

        }

    }

    data object Menu : WallpapersNavGraphTransitions {

        fun enterTransition(
            scope: AnimatedContentTransitionScope<NavBackStackEntry>
        ) = NavHostTransitions.Enter.slideInRightIntoContainerAndFadeIn(
            scope = scope,
            towards = AnimatedContentTransitionScope.SlideDirection.Left
        )

        fun exitTransition(
            scope: AnimatedContentTransitionScope<NavBackStackEntry>
        ) = NavHostTransitions.Exit.slideOutOfContainerAndFadeOut(
            scope = scope,
            towards = AnimatedContentTransitionScope.SlideDirection.Right
        )

    }

    data object WallpaperDetails : WallpapersNavGraphTransitions {

        fun enterTransition(initialRoute: Screen?): EnterTransition {
            return NavHostTransitions.Enter.scaleAndFadeInWithOvershoot(
                initialScale = when (initialRoute) {
                    is Screen.ApplyWallpaper -> 1.1f
                    else -> NavHostTransitions.DEFAULT_SCALE_TRANSITION_INITIAL_FACTOR
                }
            )
        }

        fun exitTransition(targetState: Screen?): ExitTransition {
            return NavHostTransitions.Exit.scaleAndFadeOutWithOvershoot(
                targetScale = when (targetState) {
                    is Screen.ApplyWallpaper -> 1.1f
                    else -> NavHostTransitions.DEFAULT_SCALE_TRANSITION_TARGET_FACTOR
                }
            )
        }

    }

}