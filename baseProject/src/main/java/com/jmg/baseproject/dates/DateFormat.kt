package com.jmg.baseproject.dates

import android.util.Log
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun String.formatDate(newFormat: String): String {
    try {
        // Step 1: Parse the original date string
        val originalDate = OffsetDateTime.parse(this)

        // Step 2: Convert to ZonedDateTime to extract ZoneId
        val zoneId = originalDate.toZonedDateTime().zone
        val zdt = originalDate.atZoneSameInstant(zoneId)
        // Step 3: Create SimpleDateFormat instance for the desired output format
        val endFormat = SimpleDateFormat(newFormat, Locale.getDefault())

        // Step 4: Set the timezone of SimpleDateFormat to the ZoneId extracted
        endFormat.timeZone = TimeZone.getTimeZone(zoneId)

        // Step 5: Format the date
        return endFormat.format(Date.from(zdt.toInstant()))
    } catch (e: Exception) {
        Log.e("format date", "Error: $e")
        return this
    }
}