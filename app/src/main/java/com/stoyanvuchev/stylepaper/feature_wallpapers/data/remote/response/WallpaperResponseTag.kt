package com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.response

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

@Keep
data class WallpaperResponseTag(

    @SerializedName("alias")
    val alias: String,

    @SerializedName("category")
    val category: String,

    @ColumnInfo(name = "category_id")
    @SerializedName("category_id")
    val categoryId: Int,

    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("purity")
    val purity: String

)