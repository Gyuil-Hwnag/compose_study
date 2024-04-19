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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectPhotoViewModel @Inject constructor(
    imageStorage: ImageStorage
) : BaseViewModel() {

    internal val pagingData: Flow<PagingData<SelectPhoto>> = createSelectPhotoPager(imageStorage = imageStorage)
        .flow.cachedIn(baseViewModelScope)

    private val _selectedPhotos: MutableStateFlow<List<String>> = MutableStateFlow<List<String>>(emptyList())
    val selectedPhotos: StateFlow<List<String>> = _selectedPhotos.asStateFlow()

    private val _scrollToTopEvent: MutableSharedFlow<Unit> = MutableSharedFlow()
    val scrollToTopEvent: SharedFlow<Unit> = _scrollToTopEvent.asSharedFlow()

    fun scrollToTop() {
        baseViewModelScope.launch {
            _scrollToTopEvent.emit(Unit)
        }
    }

    fun onPhotoClicked(selectPhoto: SelectPhoto) {
        val photos = selectedPhotos.value.toMutableList()
        photos.find { it == selectPhoto.imageUrl }?.let {
            photos.remove(selectPhoto.imageUrl)
            _selectedPhotos.value = photos
        }
        when (selectedPhotos.value.size) {
            0, 1 -> {
                photos.add(selectPhoto.imageUrl)
                _selectedPhotos.value = photos
            }
            else -> {
                photos.removeFirst()
                photos.add(selectPhoto.imageUrl)
                _selectedPhotos.value = photos
            }
        }
    }
}
