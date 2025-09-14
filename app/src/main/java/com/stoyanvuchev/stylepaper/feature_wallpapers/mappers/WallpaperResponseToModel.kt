package com.stoyanvuchev.stylepaper.feature_wallpapers.mappers

import com.stoyanvuchev.stylepaper.core.ext.replaceDefaultAvatar
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response.WallpaperResponseTag
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response.WallpaperResponseThumbnails
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response.WallpaperResponseUploader
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response.WallpaperResponseUploaderAvatar
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response.WallpapersListingResponseItem
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model.WallpaperTagModel
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model.WallpaperThumbnailsModel
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model.WallpaperUploaderAvatarModel
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model.WallpaperUploaderModel
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model.WallpapersListingItemModel
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.toWallpaperColors

fun WallpapersListingResponseItem.toModel() = WallpapersListingItemModel(
    id = id,
    category = category,
    colors = colors.toWallpaperColors(),
    createdAt = createdAt,
    dimensionX = dimensionX,
    dimensionY = dimensionY,
    favorites = favorites,
    fileSize = fileSize,
    fileType = fileType,
    path = path,
    purity = purity,
    ratio = ratio,
    resolution = resolution,
    shortUrl = shortUrl,
    source = source,
    thumbnails = thumbnails.toModel(),
    url = url,
    views = views
)

fun WallpaperResponseThumbnails.toModel() = WallpaperThumbnailsModel(
    large = large,
    original = original,
    small = small
)

fun WallpaperResponseTag.toModel() = WallpaperTagModel(
    id = id,
    alias = alias,
    category = category,
    createdAt = createdAt,
    name = name,
    purity = purity
)

fun WallpaperResponseUploader.toModel() = WallpaperUploaderModel(
    avatar = avatar.toModel(),
    group = group,
    username = username
)

fun WallpaperResponseUploaderAvatar.toModel() = WallpaperUploaderAvatarModel(
    large = large.replaceDefaultAvatar(),
    medium = medium.replaceDefaultAvatar(),
    small = small.replaceDefaultAvatar(),
    tiny = tiny.replaceDefaultAvatar()
)