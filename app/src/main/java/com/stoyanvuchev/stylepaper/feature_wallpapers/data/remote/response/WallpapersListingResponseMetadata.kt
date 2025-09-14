package com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

@Keep
data class WallpapersListingResponseMetadata(

    @ColumnInfo(name = "current_page")
    @SerializedName("current_page")
    val currentPage: Int,

    @ColumnInfo(name = "last_page")
    @SerializedName("last_page")
    val lastPage: Int,

    @ColumnInfo(name = "per_page")
    @SerializedName("per_page")
    val perPage: String,

    @SerializedName("query")
    val query: String? = null,

    @SerializedName("seed")
    val seed: String? = null,

    @SerializedName("total")
    val total: Int

)