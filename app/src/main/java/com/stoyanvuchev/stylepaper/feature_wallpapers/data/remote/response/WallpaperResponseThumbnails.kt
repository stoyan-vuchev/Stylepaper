package com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class WallpaperResponseThumbnails(

    @SerializedName("large")
    val large: String,

    @SerializedName("original")
    val original: String,

    @SerializedName("small")
    val small: String

)