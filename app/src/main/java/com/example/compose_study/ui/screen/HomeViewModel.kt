package com.example.compose_study.ui.screen

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.compose_study.domain.GetPhotoListUseCase
import com.example.compose_study.model.Photo
import com.example.compose_study.ui.BaseViewModel
import com.example.compose_study.ui.paging_source.createPhotoPager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPhotoListUseCase: GetPhotoListUseCase
) : BaseViewModel() {

    val photos: Flow<PagingData<Photo>> = createPhotoPager(getPhotoListUseCase = getPhotoListUseCase)
        .flow.cachedIn(baseViewModelScope)

    private val _searchState: MutableStateFlow<String> = MutableStateFlow("")
    val searchState: StateFlow<String> = _searchState.asStateFlow()

    fun searchAuthor(text: String) {
        _searchState.value = text
    }
}
