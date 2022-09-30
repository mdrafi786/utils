package com.mdrafi.utils

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.util.DisplayMetrics
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/*
* Convert Date string
* "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" - > "dd/MM/YYYY"
* */
fun findDateFromUTC(date: String): String {
    return try {
        val inputFormat = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            Locale.getDefault()
        )
        val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        outputFormat.format(inputFormat.parse(date)!!)
    } catch (e: ParseException) {
        ""
    }
}

/*
* Convert Date string
* "yyyy-MM-dd'T'HH:mm:ss'Z'" - > "dd/MM/YYYY \n hh:mm aa"
* */
fun findDateAndTimeFromUTC(date: String): String {
    return try {
        val inputFormat =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MMMM dd", Locale.getDefault())
        val newDate: Date = inputFormat.parse(date)!!
        val formattedDate = outputFormat.format(newDate)
        val formattedTime =
            SimpleDateFormat("hh:mm aa", Locale.getDefault()).format(newDate)
        "$formattedDate\n$formattedTime"
    } catch (e: ParseException) {
        ""
    }
}

/*
* Convert Date string
* "yyyy-MM-dd'T'HH:mm:ss'Z'" - > "MMMM dd, yyyy hh:mm aa
* */
fun findDateTimeInMMddyyFormat(time: String): String {
    return try {
        val inputFormat =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val outputFormat =
            SimpleDateFormat("MMMM dd, yyyy hh:mm aa", Locale.getDefault())
        val date: Date = inputFormat.parse(time)!!
        return outputFormat.format(date)
    } catch (e: ParseException) {
        ""
    }
}

/*
* Get date from timeStamp
* */
fun getDateFromTimestamp(time: Long): String {
    val date = Date(time * 1000L)
    val sdf = SimpleDateFormat("MMMM dd, yyyy hh:mm aa", Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("GMT-4")
    return sdf.format(date)
}

/*
* Get time from Timestamp
* */
fun getTimeFromTimestamp(time: Long): String {
    val timeD = Date(time * 1000L)
    val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return sdf.format(timeD)
}

/*
* Change language
* */
fun Activity.changeLocale(locale: String) {
    val dm: DisplayMetrics = resources.displayMetrics
    val conf: Configuration = resources.configuration
    conf.setLocale(Locale(locale))
    resources.updateConfiguration(conf, dm)
}

/*
* Get timeStamp from Date
* */
fun findTimeStampFromDate(dateString: String): Long {
    return try {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        val date: Date = sdf.parse(dateString)!!
        return date.time
    } catch (e: ParseException) {
        0L
    }

}

/*
* Get time from Timestamp
* */
fun findTimeFromTimestamp(time: String): String {
    var newTime = ""
    try {
        val sdf = SimpleDateFormat("H:mm", Locale.getDefault())
        val dateObj: Date = sdf.parse(time)!!
        newTime =
            SimpleDateFormat("hh:mm aa", Locale.getDefault()).format(dateObj)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return newTime
}

/*
* Find time ago string from given time like just now, one minute ago etc.
* */

fun findTimeAgo(context: Context, time: Double): String {
    val currentTime = System.currentTimeMillis()
    val timeDiff = currentTime - time
    val secondMiles = 1000.0
    val minuteMiles: Double = 60 * secondMiles
    val hourMiles: Double = 60 * minuteMiles
    val dayMiles: Double = 24 * hourMiles
    val monthMiles: Double = 30 * dayMiles
    val yearMiles: Double = 12 * monthMiles

    when {
        timeDiff < minuteMiles -> {
            return context.getString(R.string.just_now)
        }

        timeDiff < 2 * minuteMiles -> {
            return context.getString(R.string.a_minute_ago)
        }

        timeDiff < 60 * minuteMiles -> {
            return (timeDiff / minuteMiles).toInt()
                .toString() + " " + context.getString(R.string.minutes_ago)
        }

        timeDiff < 2 * hourMiles -> {
            return context.getString(R.string.an_hour_ago)
        }

        timeDiff < 24 * hourMiles -> {
            return (timeDiff / hourMiles).toInt()
                .toString() + " " + context.getString(R.string.hours_ago)
        }

        timeDiff < 48 * hourMiles -> {
            return context.getString(R.string.yesterday)
        }

        timeDiff < 30 * dayMiles -> {
            return (timeDiff / dayMiles).toInt()
                .toString() + " " + context.getString(R.string.days_ago)
        }

        timeDiff < 12 * monthMiles -> {
            return (timeDiff / monthMiles).toInt()
                .toString() + " " + context.getString(R.string.month_ago)
        }

        else -> {
            return (timeDiff / yearMiles).toInt()
                .toString() + " " + context.getString(R.string.year_ago)
        }
    }
}
