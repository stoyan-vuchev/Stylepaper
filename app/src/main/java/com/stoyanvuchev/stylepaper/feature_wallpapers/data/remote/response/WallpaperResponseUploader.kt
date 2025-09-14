package com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class WallpaperResponseUploader(

    @SerializedName("avatar")
    val avatar: WallpaperResponseUploaderAvatar,

    @SerializedName("group")
    val group: String,

    @SerializedName("username")
    val username: String

)