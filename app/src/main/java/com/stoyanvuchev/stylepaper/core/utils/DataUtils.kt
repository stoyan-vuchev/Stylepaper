package com.stoyanvuchev.stylepaper.core.utils

import java.text.CharacterIterator
import java.text.StringCharacterIterator
import java.util.Locale
import kotlin.text.format

object DataUtils {

    fun Long.toHumanReadableByteCountBin(): String {

        var bytes = this

        if (-1000 < bytes && bytes < 1000) {
            return "$bytes B"
        }

        val iterator: CharacterIterator = StringCharacterIterator("kMGTPE")

        while (bytes <= -999950 || bytes >= 999950) {
            bytes /= 1000
            iterator.next()
        }

        return String.format(
            Locale.getDefault(),
            "%.1f %cB",
            bytes / 1000.0,
            iterator.current()
        )

    }

}