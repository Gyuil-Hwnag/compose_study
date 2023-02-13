package com.example.compose_study.ui.screen

import com.example.compose_study.model.TimeItem
import com.example.compose_study.model.getDateDay
import com.example.compose_study.ui.BaseViewModel
import com.soywiz.klock.*
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
) : BaseViewModel() {

    val dateList = mutableListOf<String>().apply {
        val date = Calendar.getInstance()
        date.time = Date()

        for(i in 0 .. 14) {
            this.add(date.time.getDateDay())
            date.add(Calendar.DATE, 1)
        }
    }

    val timeItems = (0..23)
        .flatMap { hour ->
            listOf(
                TimeItem(dateTime = DateTime.now().date + Time(hour.hours), span = 30.minutes),
                TimeItem(dateTime = DateTime.now().date + Time(hour.hours + 30.minutes), span = 30.minutes)
            )
        }
}
