package com.stoyanvuchev.stylepaper.core.ext

import androidx.lifecycle.SavedStateHandle

/** Get the last variable/object state from SavedStateHandle */
fun <T> SavedStateHandle.restoreState(
    key: String,
    initialState: T
): T = this[key] ?: initialState

/** Save the variable/object state in SavedStateHandle */
fun <T> SavedStateHandle.saveState(
    key: String,
    newState: T
) {
    this[key] = newState
}