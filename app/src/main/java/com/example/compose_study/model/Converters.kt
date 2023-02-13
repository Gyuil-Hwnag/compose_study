package com.example.compose_study.model

import java.text.SimpleDateFormat
import java.util.*

fun Date.convertToString(): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
    return formatter.format(this)
}

fun String.convertToDate(): Date {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
    return formatter.parse(this)
}
