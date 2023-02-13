package com.example.compose_study.model

import android.annotation.SuppressLint
import android.util.Log
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Date.convertToString(): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
    return formatter.format(this)
}

@SuppressLint("SimpleDateFormat")
fun String.convertToDate(): Date {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
    return formatter.parse(this)
}

@SuppressLint("SimpleDateFormat")
fun String.convertToHourMinute(): Date {
    val formatter = SimpleDateFormat("HH:mm")
    return formatter.parse(this)
}

@SuppressLint("SimpleDateFormat")
fun Int.getDateDay(): String {
    return when (this) {
        0 -> "일"
        1 -> "월"
        2 -> "화"
        3 -> "수"
        4 -> "목"
        5 -> "금"
        6 -> "토"
        else -> ""
    }
}

@SuppressLint("SimpleDateFormat")
fun Date.getDateDay(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
    return dateFormat.format(this)
}
