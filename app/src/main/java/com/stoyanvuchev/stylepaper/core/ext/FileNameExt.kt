package com.stoyanvuchev.stylepaper.core.ext

fun getFileName(
    id: String,
    type: String
): String = id + when (type) {
    "JPEG" -> ".jpg"
    "PNG" -> ".png"
    "image/jpeg" -> ".jpg"
    "image/png" -> ".png"
    else -> ""
}