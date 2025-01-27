package com.example.newz

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

object Utils {

    fun parseTimestampManually(timestamp: String): String {
        // Remove the "Z" (UTC indicator) and split the timestamp into date and time
        val dateTimeString = timestamp.removeSuffix("Z") // Remove 'Z'
        val (datePart, timePart) = dateTimeString.split("T") // Split into date and time

        // Split the date and time into individual components
        val (year, month, day) = datePart.split("-").map { it.toInt() }
        val (hour, minute, second) = timePart.split(":").map { it.toInt() }

        // Create a Calendar instance to work with the date and time
        val calendar = Calendar.getInstance().apply {
            set(year, month - 1, day, hour, minute, second) // Calendar months are 0-indexed
            set(Calendar.MILLISECOND, 0)
            timeZone = TimeZone.getTimeZone("UTC") // Set to UTC
        }

        // Format the date into a more readable string (e.g., "08 Dec 2024, 04:24 AM")
        val formatter = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
        formatter.timeZone = TimeZone.getDefault() // Convert to local time zone
        return formatter.format(calendar.time)
    }

}