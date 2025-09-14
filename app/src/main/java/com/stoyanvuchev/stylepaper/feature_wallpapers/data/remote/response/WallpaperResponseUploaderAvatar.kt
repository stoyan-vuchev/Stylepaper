package com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class WallpaperResponseUploaderAvatar(

    @SerializedName("200px")
    val large: String,

    @SerializedName("128px")
    val medium: String,

    @SerializedName("32px")
    val small: String,

    @SerializedName("20px")
    val tiny: String

)