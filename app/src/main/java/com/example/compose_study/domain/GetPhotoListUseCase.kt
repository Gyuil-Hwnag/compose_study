package com.example.compose_study.domain

import com.example.compose_study.model.Photo
import javax.inject.Inject

class GetPhotoListUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(page: Int, limit: Int): List<Photo> = repository.getPhotoList(page = page, limit = limit)
}
