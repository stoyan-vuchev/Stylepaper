package com.stoyanvuchev.stylepaper.core.ui.navhost

import android.view.animation.OvershootInterpolator

/**
 * Enter and Exit transitions for Animated NavHost
 * */
object NavHostTransitions {

    // A bit of tension
    private val overshootInterpolator = OvershootInterpolator(0.8f)

    // Transition durations
    const val DEFAULT_TRANSITION_DURATION = 350
    const val MEDIUM_TRANSITION_DURATION = 250
    const val SHORT_TRANSITION_DURATION = 200

    // Fade transitions factors
    const val DEFAULT_FADE_IN_TRANSITION_FACTOR = 4

    // Scale transitions factors
    const val DEFAULT_SCALE_TRANSITION_INITIAL_FACTOR = 0.8f
    const val DEFAULT_SCALE_TRANSITION_TARGET_FACTOR = 0.8f

    // Enter Transitions
    val Enter by lazy { NavHostEnterTransitions(overshootInterpolator = overshootInterpolator) }

    // Exit Transitions
    val Exit by lazy { NavHostExitTransitions(overshootInterpolator = overshootInterpolator) }

}