package com.stoyanvuchev.stylepaper.feature_wallpapers.framework.utils

import android.content.Context
import android.os.Environment
import com.stoyanvuchev.stylepaper.core.ext.getFileName
import java.io.File

fun isWallpaperDownloaded(
    context: Context,
    id: String,
    fileType: String
): Boolean {

    val fileName = getFileName(id, fileType)
    val dir = File(
        context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
        "Stylepaper"
    )

    val file = File(dir, fileName)

    println("AJA: type: $fileType, Name: $fileName, Path: ${file.absolutePath}")
    return file.exists()
}
