package com.stoyanvuchev.stylepaper.core.ui.event

import com.stoyanvuchev.stylepaper.core.etc.UIString

sealed interface SnackbarEvent {
    data class Snackbar(val msg: UIString) : SnackbarEvent
}