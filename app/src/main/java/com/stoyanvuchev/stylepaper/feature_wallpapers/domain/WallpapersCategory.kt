package com.stoyanvuchev.stylepaper.feature_wallpapers.domain

sealed class WallpapersCategory(val value: String) {

    companion object {

        val wallpaperCategories = listOf(
            None, Abstract, Nature, Animals, Gradient,
            Cityscape, Patterns, Anime
        )

    }

    data object None : WallpapersCategory("")
    data object Abstract : WallpapersCategory("abstract")
    data object Nature : WallpapersCategory("nature")
    data object Animals : WallpapersCategory("animals")
    data object Gradient : WallpapersCategory("gradient")
    data object Cityscape : WallpapersCategory("cityscape")
    data object Patterns : WallpapersCategory("patterns")
    data object Anime : WallpapersCategory("anime")

}