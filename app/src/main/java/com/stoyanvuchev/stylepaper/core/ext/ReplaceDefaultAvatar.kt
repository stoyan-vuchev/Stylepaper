package com.stoyanvuchev.stylepaper.core.ext

fun String.replaceDefaultAvatar(): String {
    return when (this) {
        "https://wallhaven.cc/images/user/avatar/200/default-avatar.jpg" -> ""
        "https://wallhaven.cc/images/user/avatar/128/default-avatar.jpg" -> ""
        "https://wallhaven.cc/images/user/avatar/32/default-avatar.jpg" -> ""
        "https://wallhaven.cc/images/user/avatar/20/default-avatar.jpg" -> ""
        else -> this
    }
}