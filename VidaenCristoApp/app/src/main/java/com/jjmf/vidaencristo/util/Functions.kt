package com.jjmf.vidaencristo.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.format(pattern: String): String? {
    return try {
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        formatter.format(this)
    } catch (e: Exception) {
        null
    }
}