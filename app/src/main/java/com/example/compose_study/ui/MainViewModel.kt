package com.example.compose_study.ui

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.compose_study.domain.GetPhotoListUseCase
import com.example.compose_study.model.Photo
import com.example.compose_study.ui.paging_source.createPhotoPager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPhotoListUseCase: GetPhotoListUseCase
) : BaseViewModel() {

    val photos: Flow<PagingData<Photo>> = createPhotoPager(getPhotoListUseCase = getPhotoListUseCase)
        .flow.cachedIn(baseViewModelScope)
}
