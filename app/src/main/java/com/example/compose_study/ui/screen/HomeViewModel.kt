package com.example.compose_study.ui.screen

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.compose_study.domain.GetPhotoListUseCase
import com.example.compose_study.model.Photo
import com.example.compose_study.ui.BaseViewModel
import com.example.compose_study.ui.paging_source.createPhotoPager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getPhotoListUseCase: GetPhotoListUseCase
) : BaseViewModel() {

    val photos: Flow<PagingData<Photo>> = createPhotoPager(getPhotoListUseCase = getPhotoListUseCase)
        .flow.cachedIn(baseViewModelScope)

    private val _searchState: MutableStateFlow<String> = MutableStateFlow("")
    val searchState: StateFlow<String> = _searchState.asStateFlow()

    private val _scrollToTopEvent: MutableSharedFlow<Unit> = MutableSharedFlow()
    val scrollToTopEvent: SharedFlow<Unit> = _scrollToTopEvent.asSharedFlow()

    fun searchAuthor(text: String) {
        _searchState.value = text
    }

    fun scrollToTop() {
        baseViewModelScope.launch {
            _scrollToTopEvent.emit(Unit)
        }
    }
}
