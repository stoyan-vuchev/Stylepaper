package com.stoyanvuchev.stylepaper.feature_wallpapers.framework.manager

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class DownloadProgressManager @Inject constructor() {

    private val _progressMap = MutableStateFlow<Map<String, Int>>(emptyMap())
    val progressMap: StateFlow<Map<String, Int>> = _progressMap.asStateFlow()

    fun update(id: String, progress: Int) {
        _progressMap.value = _progressMap.value.toMutableMap().apply {
            this[id] = progress
        }
    }

    fun remove(id: String) {
        _progressMap.value = _progressMap.value.toMutableMap().apply {
            remove(id)
        }
    }

}