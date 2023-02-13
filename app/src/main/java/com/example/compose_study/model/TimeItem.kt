package com.example.compose_study.model

import com.soywiz.klock.DateTime
import com.soywiz.klock.Time
import com.soywiz.klock.TimeSpan
import com.soywiz.klock.milliseconds

data class TimeItem(
    val dateTime: DateTime,
    val span: TimeSpan
) {

    val hour: Int
        get() = dateTime.hours

    val minutes: Int
        get() = dateTime.minutes

    val range: ClosedRange<Time>
        get() = dateTime.time..(dateTime + (span - 1.milliseconds)).time

    val isDimmed: Boolean
        get() = dateTime < DateTime.nowLocal().local

}

