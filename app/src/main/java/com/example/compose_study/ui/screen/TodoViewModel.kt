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
class TodoViewModel @Inject constructor(
) : BaseViewModel() {
}
