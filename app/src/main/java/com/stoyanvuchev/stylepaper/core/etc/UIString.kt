package com.stoyanvuchev.stylepaper.core.etc

import android.content.Context
import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.res.stringResource
import kotlinx.parcelize.Parcelize

@Parcelize
sealed interface UIString : Parcelable {

    data class Basic(
        val value: String
    ) : UIString

    class Resource(
        @param:StringRes val resId: Int,
        // vararg val args: Any
    ) : UIString

    @Stable
    @Composable
    fun asString(): String = when (this) {
        is Basic -> this.value
        is Resource -> stringResource(resId /* *args */)
    }

    fun asString(context: Context): String = when (this) {
        is Basic -> this.value
        is Resource -> context.getString(resId /* *args */)
    }

}