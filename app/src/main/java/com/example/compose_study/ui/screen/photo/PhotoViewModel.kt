package com.example.compose_study.ui.screen.photo

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.compose_study.ui.BaseViewModel
import com.example.compose_study.utils.ImageStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    imageStorage: ImageStorage
) : BaseViewModel() {

    internal val pagingData: Flow<PagingData<PhotoListItem>> = createImageItemPager(imageStorage = imageStorage)
        .flow.cachedIn(baseViewModelScope)

    private val _scrollToTopEvent: MutableSharedFlow<Unit> = MutableSharedFlow()
    val scrollToTopEvent: SharedFlow<Unit> = _scrollToTopEvent.asSharedFlow()

    fun scrollToTop() {
        baseViewModelScope.launch {
            _scrollToTopEvent.emit(Unit)
        }
    }

}
