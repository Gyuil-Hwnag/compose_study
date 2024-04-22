package com.example.compose_study.ui.screen.photo.select

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.compose_study.ui.BaseViewModel
import com.example.compose_study.utils.ImageStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectPhotoViewModel @Inject constructor(
    imageStorage: ImageStorage
) : BaseViewModel() {

    internal val pagingData: Flow<PagingData<SelectPhoto>> = createSelectPhotoPager(imageStorage = imageStorage)
        .flow.cachedIn(baseViewModelScope)

    private val _selectedPhotos: MutableStateFlow<List<SelectPhoto>> = MutableStateFlow<List<SelectPhoto>>(emptyList())
    val selectedPhotos: StateFlow<List<SelectPhoto>> = _selectedPhotos.asStateFlow()

    val hasSelected: StateFlow<Boolean> = selectedPhotos.map { it.size < SELECT_LIMIT }
        .stateIn(
            baseViewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = true
        )

    private val _overPickToastMessage: MutableSharedFlow<Unit> = MutableSharedFlow<Unit>()
    val overSelectToastMessage: SharedFlow<Unit> = _overPickToastMessage.asSharedFlow()

    private val _scrollToTopEvent: MutableSharedFlow<Unit> = MutableSharedFlow()
    val scrollToTopEvent: SharedFlow<Unit> = _scrollToTopEvent.asSharedFlow()

    fun scrollToTop() {
        baseViewModelScope.launch {
            _scrollToTopEvent.emit(Unit)
        }
    }

    fun onPhotoClicked(selectPhoto: SelectPhoto) {
        baseViewModelScope.launch {
            if (selectedPhotos.value.size < SELECT_LIMIT) {
                val photos = selectedPhotos.value.toMutableList().apply {
                    add(selectPhoto)
                }
                _selectedPhotos.value = photos
            } else {
                _overPickToastMessage.emit(Unit)
            }
        }
    }

    fun onDeselectPhoto(selectPhoto: SelectPhoto) {
        val photos = selectedPhotos.value.toMutableList()
        photos.remove(selectPhoto)
        _selectedPhotos.value = photos
    }
}

const val SELECT_LIMIT = 5
