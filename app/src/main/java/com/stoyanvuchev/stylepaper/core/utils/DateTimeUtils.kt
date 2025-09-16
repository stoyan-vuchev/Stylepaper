package com.stoyanvuchev.stylepaper.core.utils

import java.text.SimpleDateFormat
import java.util.*
import kotlin.let

object DateTimeUtils {

    fun String.toReadableDateTimeFormat(): String {

        val simpleDateFormat = SimpleDateFormat(
            "yyyy-MM-dd",
            Locale.getDefault()
        )

        return simpleDateFormat.parse(this)?.let {
            SimpleDateFormat(
                "EEEE, d MMM yyyy",
                Locale.getDefault()
            ).format(it)
        } ?: ""

    }

}