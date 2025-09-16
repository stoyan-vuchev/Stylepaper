package com.stoyanvuchev.stylepaper.feature_wallpapers.framework.utils

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import com.stoyanvuchev.stylepaper.R

fun setWallpaperWithChooser(
    context: Context,
    id: String,
    fileType: String
) {

    val file = getWallpaperFile(context, id, fileType)
    if (!file.exists()) return

    val uri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        file
    )

    val intent = Intent(Intent.ACTION_ATTACH_DATA).apply {
        setDataAndType(uri, "image/*")
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        putExtra("mimeType", "image/*")
    }

    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.set_as)
        )
    )

}