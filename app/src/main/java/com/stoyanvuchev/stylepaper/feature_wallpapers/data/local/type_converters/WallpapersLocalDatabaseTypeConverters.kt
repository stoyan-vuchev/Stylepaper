package com.stoyanvuchev.stylepaper.feature_wallpapers.data.local.type_converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stoyanvuchev.stylepaper.core.etc.UIString
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response.WallpaperResponseTag
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response.WallpaperResponseThumbnails
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response.WallpaperResponseUploader
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response.WallpaperResponseUploaderAvatar
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response.WallpapersListingResponseItem
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpaperColor
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpapersCategory
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpapersHomeSelection

/**
 *
 * Since Room only operates with Primitive data types (String, Integer, Boolean etc.)
 * it is required to convert Non-primitive data objects to JSON strings.
 *
 * */

class WallpapersLocalDatabaseTypeConverters {

    @TypeConverter
    fun fromJsonToWallpaperResponseUploaderAvatar(
        value: String?
    ): WallpaperResponseUploaderAvatar? {
        val type = object : TypeToken<WallpaperResponseUploaderAvatar>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromWallpaperResponseUploaderAvatarToJson(
        value: WallpaperResponseUploaderAvatar?
    ): String? {
        val type = object : TypeToken<WallpaperResponseUploaderAvatar>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun fromJsonToWallpapersListingResponseItemList(
        value: String?
    ): List<WallpapersListingResponseItem>? {
        val type = object : TypeToken<List<WallpapersListingResponseItem>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromWallpapersListingResponseItemListToJson(
        value: List<WallpapersListingResponseItem>?
    ): String? {
        val type = object : TypeToken<List<WallpapersListingResponseItem>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun fromJsonToWallpapersHomeSelection(
        value: String?
    ): WallpapersHomeSelection? {
        val type = object : TypeToken<WallpapersHomeSelection>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromWallpapersHomeSelectionToJson(
        value: WallpapersHomeSelection?
    ): String? {
        val type = object : TypeToken<WallpapersHomeSelection>() {}.type
        return Gson().toJson(value, type)
    }


    @TypeConverter
    fun fromJsonToWallpapersCategory(
        value: String?
    ): WallpapersCategory? {
        val type = object : TypeToken<WallpapersCategory>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromWallpapersCategoryToJson(
        value: WallpapersCategory?
    ): String? {
        val type = object : TypeToken<WallpapersCategory>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun fromJsonToWallpaperResponseThumbnails(
        value: String?
    ): WallpaperResponseThumbnails? {
        val type = object : TypeToken<WallpaperResponseThumbnails>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromWallpaperResponseThumbnailsToJson(
        value: WallpaperResponseThumbnails?
    ): String? {
        val type = object : TypeToken<WallpaperResponseThumbnails>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun fromJsonToWallpaperResponseTagList(
        value: String?
    ): List<WallpaperResponseTag>? {
        val type = object : TypeToken<List<WallpaperResponseTag>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromWallpaperResponseTagListToJson(
        value: List<WallpaperResponseTag>?
    ): String? {
        val type = object : TypeToken<List<WallpaperResponseTag>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun fromJsonToWallpaperResponseUploader(
        value: String?
    ): WallpaperResponseUploader? {
        val type = object : TypeToken<WallpaperResponseUploader>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromWallpaperResponseUploaderToJson(
        value: WallpaperResponseUploader?
    ): String? {
        val type = object : TypeToken<WallpaperResponseUploader>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun fromJsonToColorsList(
        value: String?
    ): List<String>? {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromColorsListToJson(
        value: List<String>?
    ): String? {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun fromJsonToUIString(
        value: String?
    ): UIString? {
        val type = object : TypeToken<UIString>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromUIStringToJson(
        value: UIString?
    ): String? {
        val type = object : TypeToken<UIString>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun fromJsonToWallpaperColor(
        value: String?
    ): WallpaperColor? {
        val type = object : TypeToken<WallpaperColor>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromWallpaperColorToJson(
        value: WallpaperColor?
    ): String? {
        val type = object : TypeToken<WallpaperColor>() {}.type
        return Gson().toJson(value, type)
    }

}