package com.example.compose_study.ui.screen

import com.example.compose_study.model.TimeItem
import com.example.compose_study.model.getCalendarDate
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
        listOf(date.time.getCalendarDate())
    }

    val timeItems = (0..23).flatMap { hour ->
        listOf(
            TimeItem(dateTime = DateTime.now().date + Time(hour.hours), span = 30.minutes),
            TimeItem(dateTime = DateTime.now().date + Time(hour.hours + 30.minutes), span = 30.minutes)
        )
    }

    private val _currentTimeIndex: MutableStateFlow<Int> = MutableStateFlow<Int>(0)
    val currentTimeIndex: StateFlow<Int> = _currentTimeIndex.asStateFlow()

    private val _currentDayIndex: MutableStateFlow<Pair<String, Int>> = MutableStateFlow<Pair<String, Int>>(Pair(first = "", second = 0))
    val currentDayIndex: StateFlow<Pair<String, Int>> = _currentDayIndex.asStateFlow()

    private val _currentDay: MutableStateFlow<String> = MutableStateFlow<String>("")
    val currentDay: StateFlow<String> = _currentDay.asStateFlow()

    init {
        val day = Calendar.getInstance().time.getCalendarDate()
        val time = DateTimeTz.nowLocal().local.time

        _currentDayIndex.value = Pair(first = day, second = dates.indexOfFirst { it.contains(day) })
        _currentDay.value = day
        _currentTimeIndex.value = timeItems.map { it.range }.indexOfFirst { timeRange -> timeRange.contains(time) }
    }

    fun onDayClicked(day: String) {
        _currentDay.value = day
        _currentDayIndex.value = Pair(first = day, second = dates.indexOfFirst { it.contains(day) })

        if(day != Calendar.getInstance().time.getCalendarDate()) {
            _currentTimeIndex.value = 20
        } else {
            _currentTimeIndex.value = timeItems.map { it.range }.indexOfFirst { timeRange -> timeRange.contains(DateTimeTz.nowLocal().local.time) }
        }
    }
}
