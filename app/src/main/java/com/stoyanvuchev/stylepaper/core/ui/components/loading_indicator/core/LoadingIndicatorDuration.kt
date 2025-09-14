package com.stoyanvuchev.stylepaper.core.ui.components.loading_indicator.core

/** Loading Indicator animation durations */
sealed class LoadingIndicatorDuration(
    val millis: Int,
    val start: Int = 0,
    val halfWay: Int = millis / 2,
    val end: Int = millis
) {

    /** The Longest animation duration: 1120 milliseconds */
    object Long : LoadingIndicatorDuration(millis = 9024)

    /** The Normal animation duration: 960 milliseconds */
    object Normal : LoadingIndicatorDuration(millis = 768)

    /** The Shortest animation duration: 640 milliseconds */
    object Short : LoadingIndicatorDuration(millis = 512)

}