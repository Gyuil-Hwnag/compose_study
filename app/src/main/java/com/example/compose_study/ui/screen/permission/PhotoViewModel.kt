package com.example.compose_study.ui.screen.permission

import com.example.compose_study.ui.BaseViewModel
import com.example.compose_study.utils.ImageBucket
import com.example.compose_study.utils.ImageStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val imageStorage: ImageStorage
) : BaseViewModel() {

    private val _photos: MutableStateFlow<List<ImageBucket>> = MutableStateFlow<List<ImageBucket>>(emptyList())
    val photos: StateFlow<List<ImageBucket>> = _photos.asStateFlow()
}
