package com.example.compose_study.ui.screen

import android.util.Log
import com.example.compose_study.model.TimeItem
import com.example.compose_study.model.getDateDay
import com.example.compose_study.ui.BaseViewModel
import com.soywiz.klock.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
) : BaseViewModel() {

    private val date: Calendar = Calendar.getInstance()

    val dates = (-14..14).flatMap { index ->
        date.time = Date()
        date.add(Calendar.DATE, index)
        listOf(date.time.getDateDay())
    }

    val timeItems = (0..23).flatMap { hour ->
        listOf(
            TimeItem(dateTime = DateTime.now().date + Time(hour.hours), span = 30.minutes),
            TimeItem(dateTime = DateTime.now().date + Time(hour.hours + 30.minutes), span = 30.minutes)
        )
    }

    private val _currentTimeIndex: MutableStateFlow<Int> = MutableStateFlow<Int>(0)
    val currentTimeIndex: StateFlow<Int> = _currentTimeIndex.asStateFlow()

    private val _currentDayIndex: MutableStateFlow<Int> = MutableStateFlow<Int>(0)
    val currentDayIndex: StateFlow<Int> = _currentDayIndex.asStateFlow()

    init {
        _currentDayIndex.value = dates.indexOfFirst { it.contains(Calendar.getInstance().time.getDateDay()) }
        _currentTimeIndex.value = timeItems.map { it.range }.indexOfFirst { timeRange -> timeRange.contains(DateTimeTz.nowLocal().local.time) }
    }

    fun onDayClicked(day: String) {
        _currentDayIndex.value = dates.indexOfFirst { it.contains(day) }
    }
}
