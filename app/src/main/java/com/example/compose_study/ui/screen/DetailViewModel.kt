package com.example.compose_study.ui.screen

import com.example.compose_study.domain.GetPhotoUseCase
import com.example.compose_study.model.Photo
import com.example.compose_study.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getPhotoUseCase: GetPhotoUseCase
) : BaseViewModel() {

    private val _photoState: MutableStateFlow<Photo?> = MutableStateFlow(null)
    val photoState: StateFlow<Photo?> = _photoState.asStateFlow()

    fun getPhoto(id: String) {
        baseViewModelScope.launch {
            _photoState.value = getPhotoUseCase.invoke(id = id)
        }
    }
}
