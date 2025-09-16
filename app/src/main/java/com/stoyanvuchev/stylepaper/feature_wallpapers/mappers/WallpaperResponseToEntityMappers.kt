package com.stoyanvuchev.stylepaper.feature_wallpapers.mappers

import com.stoyanvuchev.stylepaper.feature_wallpapers.data.local.entity.WallpaperEntity
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.local.entity.WallpapersDiscoverListingEntity
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.local.entity.WallpapersHomeListingEntity
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.local.entity.WallpapersSearchListingEntity
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response.WallpaperResponse
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response.WallpapersListingResponse
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpaperColor
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpapersCategory
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpapersHomeSelection

fun WallpapersListingResponse.toHomeEntity(
    fetched: Long,
    selection: WallpapersHomeSelection
) = WallpapersHomeListingEntity(
    fetched = fetched,
    selection = selection,
    wallpapers = data.distinct(),
    currentPage = metadata.currentPage,
    lastPage = metadata.lastPage,
    perPage = metadata.perPage,
    seed = metadata.seed ?: "",
    total = metadata.total
)

fun WallpapersListingResponse.toDiscoverEntity(
    fetched: Long,
    category: WallpapersCategory,
    color: WallpaperColor
) = WallpapersDiscoverListingEntity(
    fetched = fetched,
    category = category,
    color = color,
    wallpapers = data.distinct(),
    currentPage = metadata.currentPage,
    lastPage = metadata.lastPage,
    perPage = metadata.perPage,
    seed = metadata.seed ?: "",
    total = metadata.total
)

fun WallpapersListingResponse.toSearchEntity(
    fetched: Long,
    searchQuery: String,
    category: WallpapersCategory,
    color: WallpaperColor
) = WallpapersSearchListingEntity(
    fetched = fetched,
    searchQuery = searchQuery,
    category = category,
    color = color,
    wallpapers = data.distinct(),
    currentPage = metadata.currentPage,
    lastPage = metadata.lastPage,
    perPage = metadata.perPage,
    seed = metadata.seed ?: "",
    total = metadata.total
)

fun WallpaperResponse.toHomeEntity(fetched: Long) = WallpaperEntity(
    id = data.id,
    fetched = fetched,
    category = data.category,
    colors = data.colors,
    createdAt = data.createdAt,
    dimensionX = data.dimensionX,
    dimensionY = data.dimensionY,
    favorites = data.favorites,
    fileSize = data.fileSize,
    fileType = data.fileType,
    path = data.path,
    purity = data.purity,
    ratio = data.ratio,
    resolution = data.resolution,
    shortUrl = data.shortUrl,
    source = data.source,
    tags = data.tags,
    thumbs = data.thumbs,
    uploader = data.uploader,
    url = data.url,
    views = data.views
)