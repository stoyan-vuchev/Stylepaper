package com.stoyanvuchev.stylepaper.core.ui.navhost

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry
import com.stoyanvuchev.stylepaper.core.ui.navhost.NavHostTransitions.DEFAULT_FADE_IN_TRANSITION_FACTOR
import com.stoyanvuchev.stylepaper.core.ui.navhost.NavHostTransitions.DEFAULT_SCALE_TRANSITION_INITIAL_FACTOR
import com.stoyanvuchev.stylepaper.core.ui.navhost.NavHostTransitions.DEFAULT_TRANSITION_DURATION
import com.stoyanvuchev.stylepaper.core.ui.navhost.NavHostTransitions.MEDIUM_TRANSITION_DURATION
import com.stoyanvuchev.stylepaper.core.ui.navhost.NavHostTransitions.SHORT_TRANSITION_DURATION

/**
 * A collection of Enter transitions for Animated NavHost
 * */
class NavHostEnterTransitions(
    private val overshootInterpolator: OvershootInterpolator
) {

    /** A combination of scaleIn() + fadeIn() transitions and Overshoot interpolated Easing */
    fun scaleAndFadeInWithOvershoot(
        scaleInDuration: Int = DEFAULT_TRANSITION_DURATION,
        fadeInDuration: Int = SHORT_TRANSITION_DURATION,
        initialScale: Float = DEFAULT_SCALE_TRANSITION_INITIAL_FACTOR
    ): EnterTransition {
        return scaleIn(
            animationSpec = tween(
                durationMillis = scaleInDuration,
                easing = { overshootInterpolator.getInterpolation(it) }
            ), initialScale = initialScale
        ) + fadeIn(
            animationSpec = tween(
                durationMillis = fadeInDuration,
                easing = { overshootInterpolator.getInterpolation(it) }
            )
        )
    }

    /**
     * A combination of slideIntoContainer() + fadeIn() transitions
     * and Overshoot interpolated Easing
     * */
    fun slideInRightIntoContainerAndFadeIn(
        scope: AnimatedContentTransitionScope<NavBackStackEntry>,
        towards: AnimatedContentTransitionScope.SlideDirection
    ): EnterTransition {
        return scope.slideIntoContainer(
            towards = towards,
            animationSpec = tween(
                durationMillis = DEFAULT_TRANSITION_DURATION,
                easing = { overshootInterpolator.getInterpolation(it) }
            ), initialOffset = { it / DEFAULT_FADE_IN_TRANSITION_FACTOR }
        ) + fadeIn(
            animationSpec = tween(
                durationMillis = MEDIUM_TRANSITION_DURATION,
                easing = { overshootInterpolator.getInterpolation(it) }
            )
        )
    }

}