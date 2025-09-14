package com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.home

import android.os.Parcelable
import androidx.compose.runtime.Stable
import com.stoyanvuchev.stylepaper.core.etc.UIString
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpapersHomeSelection
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model.WallpapersListingModel
import kotlinx.parcelize.Parcelize

@Parcelize
@Stable
data class WallpapersHomeScreenState(
    val isLoading: Boolean = true,
    val wallpapersListing: WallpapersListingModel = WallpapersListingModel(),
    val endReached: Boolean = false,
    val selection: WallpapersHomeSelection = WallpapersHomeSelection.Latest,
    val error: UIString? = null
) : Parcelable