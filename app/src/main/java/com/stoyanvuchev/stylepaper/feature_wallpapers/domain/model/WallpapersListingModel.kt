package com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model

data class WallpapersListingModel(
    val fetched: String = "",
    var wallpapers: MutableList<WallpapersListingItemModel> = mutableListOf(),
    val currentPage: Int = 1,
    val lastPage: Int = 1,
    val perPage: String = "",
    val seed: String = "",
    val total: Int = 0
)