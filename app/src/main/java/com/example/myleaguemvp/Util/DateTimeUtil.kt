@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.example.myleaguemvp.Util

import android.annotation.SuppressLint
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateTimeUtil {

    @SuppressLint("SimpleDateFormat")
    fun formatDate(date: String, format: String = "EEEE, dd MMMM yyyy"): String {
        var formattedDate = ""
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")

        try {
            val parseDate = sdf.parse(date)
            formattedDate = SimpleDateFormat(format).format(parseDate)
        } catch (e: ParseException) {
            Log.i("ERROR PARSE DATE", "${e}")
        }
        return formattedDate
    }

    @SuppressLint("SimpleDateFormat")
    fun formatTime(time: String, format: String = "HH:mm"): String {
        var formattedTime = ""
        val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")

        try {
            val parseTime = sdf.parse(time)
            formattedTime = SimpleDateFormat(format).format(parseTime)
        } catch (e: ParseException) {
            Log.i("ERROR PARSE Time", "${e.printStackTrace()}")
        }
        return formattedTime
    }
}