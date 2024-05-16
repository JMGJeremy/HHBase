package com.jmg.baseproject

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun String.formatDate(newFormat: String): String {
    try {
        val startFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault())
        startFormat.timeZone = TimeZone.getTimeZone("UTC")
        val endFormat = SimpleDateFormat(newFormat, Locale.getDefault())
        endFormat.timeZone = TimeZone.getDefault()
        return startFormat.parse(this)?.let { endFormat.format(it) } ?: this
    }catch (e:Exception){
        return this
    }
}

fun String.toLocalDate():LocalDate{
    try {
        val formatter = DateTimeFormatter.ofPattern("\"yyyy-MM-dd'T'HH:mm:ss.SSSXXX\"")
        return LocalDate.parse(this, formatter)
    } catch (e:Exception){
        return LocalDate.now()
    }
}