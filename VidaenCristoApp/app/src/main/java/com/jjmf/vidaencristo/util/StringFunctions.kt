package com.jjmf.vidaencristo.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun String.toDate(): Date? {
    return try {
        val isoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        isoFormat.timeZone = TimeZone.getTimeZone("UTC")
        isoFormat.parse(this)
    } catch (e: Exception) {
        // Manejo de excepciones si el formato no es correcto
        e.printStackTrace()
        null
    }
}
fun String.toDate2(): Date? {
    return try {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        simpleDateFormat.parse(this)
    } catch (e: Exception) {
        // Manejo de errores en caso de un formato inválido
        e.printStackTrace()
        null
    }
}