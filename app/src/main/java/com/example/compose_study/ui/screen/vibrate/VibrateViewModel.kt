package com.example.compose_study.ui.screen.vibrate

import androidx.lifecycle.viewModelScope
import com.example.compose_study.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VibrateViewModel @Inject constructor(
) : BaseViewModel() {

    private val _vibrate: MutableSharedFlow<Pair<Long, Int>> = MutableSharedFlow<Pair<Long, Int>>()
    val vibrate: SharedFlow<Pair<Long, Int>> = _vibrate.asSharedFlow()

    fun onVibrateButtonClicked(milliseconds: Long, amplitude: Int) = viewModelScope.launch {
        _vibrate.emit(Pair(milliseconds, amplitude))
    }
}
