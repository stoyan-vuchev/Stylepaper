package com.stoyanvuchev.stylepaper.core.utils

import java.util.Locale
import kotlin.math.ln
import kotlin.math.pow

object NumberUtils {

    fun Int.getFormattedNumber(): String {

        val number = this

        return if (number > 1000) {

            val exp = (ln(number.toDouble()) / ln(1000.0)).toInt()

            String.format(
                Locale.getDefault(),
                "%.1f%c",
                number / 1000.0.pow(exp.toDouble()),
                "kMGTPE"[exp - 1]
            )

        } else "$number"

    }

}