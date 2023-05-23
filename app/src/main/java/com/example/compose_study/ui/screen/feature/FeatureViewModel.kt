package com.example.compose_study.ui.screen.feature

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.compose_study.domain.GetPhotoListUseCase
import com.example.compose_study.domain.GetPhotoUseCase
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
class FeatureViewModel @Inject constructor(
) : BaseViewModel() {
}
