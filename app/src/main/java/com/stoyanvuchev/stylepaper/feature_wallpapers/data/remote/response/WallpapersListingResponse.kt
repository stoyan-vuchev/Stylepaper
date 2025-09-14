package com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class WallpapersListingResponse(
    @SerializedName("data")
    val data: List<WallpapersListingResponseItem>,
    @SerializedName("meta")
    val metadata: WallpapersListingResponseMetadata
)