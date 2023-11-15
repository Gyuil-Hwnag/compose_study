package com.example.compose_study.ui.screen.vibrate

import androidx.lifecycle.viewModelScope
import com.example.compose_study.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VibrateViewModel @Inject constructor(
) : BaseViewModel() {

    private val _vibrate: MutableSharedFlow<Pair<Long, Int>> = MutableSharedFlow<Pair<Long, Int>>()
    val vibrate: SharedFlow<Pair<Long, Int>> = _vibrate.asSharedFlow()

    private val _vibrates: MutableStateFlow<List<Pair<Long, Int>>> = MutableStateFlow<List<Pair<Long, Int>>>(emptyList())
    val vibrates: StateFlow<List<Pair<Long, Int>>> = _vibrates.asStateFlow()

    init {
        val first = 10
        val list = ArrayList<Pair<Long, Int>>()
        (0..100).forEach {
            val vibrateItem = Pair((first+it).toLong(), first+it)
            list.add(vibrateItem)
        }
        _vibrates.value = list
    }

    fun onVibrateButtonClicked(milliseconds: Long, amplitude: Int) = viewModelScope.launch {
        _vibrate.emit(Pair(milliseconds, amplitude))
    }
}
