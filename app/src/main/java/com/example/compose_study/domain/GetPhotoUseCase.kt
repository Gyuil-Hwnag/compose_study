package com.example.compose_study.domain

import com.example.compose_study.model.Photo
import javax.inject.Inject

class GetPhotoUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(id: String): Photo = repository.getPhoto(id = id)
}
