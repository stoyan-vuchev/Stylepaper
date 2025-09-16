package com.stoyanvuchev.stylepaper.feature_wallpapers.mappers

import com.stoyanvuchev.stylepaper.core.utils.DataUtils.toHumanReadableByteCountBin
import com.stoyanvuchev.stylepaper.core.utils.ProportionsUtils
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.local.entity.WallpaperEntity
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.local.entity.WallpapersDiscoverListingEntity
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.local.entity.WallpapersHomeListingEntity
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.local.entity.WallpapersSearchListingEntity
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model.WallpaperModel
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model.WallpapersListingModel

fun WallpapersHomeListingEntity.toModel() = WallpapersListingModel(
    fetched = fetched.toString(),
    wallpapers = wallpapers.map { it.toModel() }.toMutableList(),
    currentPage = currentPage,
    lastPage = lastPage,
    perPage = perPage,
    seed = seed,
    total = total
)

fun WallpapersDiscoverListingEntity.toModel() = WallpapersListingModel(
    fetched = fetched.toString(),
    wallpapers = wallpapers.map { it.toModel() }.toMutableList(),
    currentPage = currentPage,
    lastPage = lastPage,
    perPage = perPage,
    seed = seed,
    total = total
)

fun WallpapersSearchListingEntity.toModel() = WallpapersListingModel(
    fetched = fetched.toString(),
    wallpapers = wallpapers.map { it.toModel() }.toMutableList(),
    currentPage = currentPage,
    lastPage = lastPage,
    perPage = perPage,
    seed = seed,
    total = total
)

fun WallpaperEntity.toModel() = WallpaperModel(
    id = id,
    fetched = fetched.toString(),
    category = category,
    colors = colors,
    createdAt = createdAt,
    dimensionX = dimensionX,
    dimensionY = dimensionY,
    favorites = favorites,
    fileSize = fileSize.toHumanReadableByteCountBin(),
    fileType = fileType,
    aspectRatio = if (dimensionX > 0 && dimensionY > 0) {
        ProportionsUtils.calculateAspectRatio(dimensionX, dimensionY)
    } else "",
    path = path,
    purity = purity,
    ratio = ratio,
    resolution = resolution.replace("x", " x "),
    shortUrl = shortUrl,
    source = source,
    tags = tags.map { it.toModel() },
    thumbs = thumbs.toModel(),
    uploader = uploader.toModel(),
    url = url,
    views = views
)