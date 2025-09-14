package com.stoyanvuchev.stylepaper.core.ui.navhost

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.navigation.NavBackStackEntry
import com.stoyanvuchev.stylepaper.core.ui.navhost.NavHostTransitions.DEFAULT_FADE_IN_TRANSITION_FACTOR
import com.stoyanvuchev.stylepaper.core.ui.navhost.NavHostTransitions.DEFAULT_SCALE_TRANSITION_TARGET_FACTOR
import com.stoyanvuchev.stylepaper.core.ui.navhost.NavHostTransitions.DEFAULT_TRANSITION_DURATION
import com.stoyanvuchev.stylepaper.core.ui.navhost.NavHostTransitions.SHORT_TRANSITION_DURATION

/**
 * A collection of Exit transitions for Animated NavHost
 * */
class NavHostExitTransitions(
    private val overshootInterpolator: OvershootInterpolator
) {

    /** A combination of scaleOut() + fadeOut() transitions and Overshoot interpolated Easing */
    fun scaleAndFadeOutWithOvershoot(
        scaleOutDuration: Int = DEFAULT_TRANSITION_DURATION,
        fadeOutDuration: Int = SHORT_TRANSITION_DURATION,
        targetScale: Float = DEFAULT_SCALE_TRANSITION_TARGET_FACTOR
    ): ExitTransition {
        return scaleOut(
            animationSpec = tween(
                durationMillis = scaleOutDuration,
                easing = { overshootInterpolator.getInterpolation(it) }
            ), targetScale = targetScale
        ) + fadeOut(
            animationSpec = tween(
                durationMillis = fadeOutDuration,
                easing = { overshootInterpolator.getInterpolation(it) }
            )
        )
    }

    /**
     *  A combination of slideOutOfContainer() + fadeOut() transitions
     *  and Overshoot interpolated Easing
     * */
    fun slideOutOfContainerAndFadeOut(
        scope: AnimatedContentTransitionScope<NavBackStackEntry>,
        towards: AnimatedContentTransitionScope.SlideDirection
    ): ExitTransition {
        return scope.slideOutOfContainer(
            towards = towards,
            animationSpec = tween(
                durationMillis = DEFAULT_TRANSITION_DURATION,
                easing = { overshootInterpolator.getInterpolation(it) }
            ), targetOffset = { it / DEFAULT_FADE_IN_TRANSITION_FACTOR }
        ) + fadeOut(
            animationSpec = tween(
                durationMillis = SHORT_TRANSITION_DURATION,
                easing = { overshootInterpolator.getInterpolation(it) }
            )
        )
    }

}