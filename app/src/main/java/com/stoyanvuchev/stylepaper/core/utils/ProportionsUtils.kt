package com.stoyanvuchev.stylepaper.core.utils

object ProportionsUtils {

    fun calculateAspectRatio(w: Int, h: Int): String {

        var remainder: Int
        var dividend = 0
        var divisor = 0

        return if (h != w) {

            if (h > w) {
                dividend = h
                divisor = w
            }

            if (w > h) {
                dividend = w
                divisor = h
            }

            var gcd = -1
            while (gcd == -1) {
                remainder = dividend % divisor
                if (remainder == 0) {
                    gcd = divisor
                } else {
                    dividend = divisor
                    divisor = remainder
                }
            }

            val hr = w / gcd
            val vr = h / gcd

            "$hr x $vr"

        } else "1 x 1"

    }

}