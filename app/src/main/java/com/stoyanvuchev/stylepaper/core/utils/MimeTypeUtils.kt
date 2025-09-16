package com.stoyanvuchev.stylepaper.core.utils

object MimeTypeUtils {

    fun String.toReadableMimeType(): String {
        return when (this) {
            "image/jpeg" -> "JPEG"
            "image/png" -> "PNG"
            else -> ""
        }
    }

}