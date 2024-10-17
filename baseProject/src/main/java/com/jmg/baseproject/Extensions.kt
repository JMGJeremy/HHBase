package com.jmg.baseproject

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAccessor
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Deprecated("Use baseproject.dates.DateFormat.kt")
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

fun Long.toDateFormat(format: String): String{
    val date = Date.from(Instant.ofEpochMilli(this))
    val formatter = SimpleDateFormat(format, Locale.getDefault())
    return formatter.format(date)
}

fun Long.timeBetween(): String {
    val current = LocalDateTime.now()
    val date = LocalDateTime.ofInstant(Instant.ofEpochMilli(this), TimeZone.getDefault().toZoneId())

    val diffDays = ChronoUnit.DAYS.between(date, current)
    val diffWeeks = ChronoUnit.WEEKS.between(date, current)
    val diffMonths = ChronoUnit.MONTHS.between(date, current)
    val diffYears = ChronoUnit.YEARS.between(date, current)

    return when {
        diffDays == 0L -> "Today"
        diffDays == 1L -> "Yesterday"
        diffWeeks == 1L -> "1 week ago"
        diffWeeks in 2..4 -> "$diffWeeks weeks ago"
        diffMonths == 1L -> "1 month ago"
        diffMonths in 2..11 -> "$diffMonths months ago"
        diffYears == 1L -> "1 year ago"
        else -> "$diffYears years ago"
    }
}