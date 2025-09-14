package com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class WallpaperResponse(
    @SerializedName("data")
    val data: WallpaperResponseData
)