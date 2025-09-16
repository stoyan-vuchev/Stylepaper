package com.stoyanvuchev.stylepaper.feature_wallpapers.framework.utils

import android.content.Context
import android.os.Environment
import com.stoyanvuchev.stylepaper.core.ext.getFileName
import java.io.File

fun isWallpaperDownloaded(
    context: Context,
    id: String,
    fileType: String
): Boolean = getWallpaperFile(
    context = context,
    id = id,
    fileType = fileType
).exists()

fun getWallpaperFile(
    context: Context,
    id: String,
    fileType: String
): File {

    val fileName = getFileName(id, fileType)
    val dir = File(
        context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
        "Stylepaper"
    )

    return File(dir, fileName)

}
