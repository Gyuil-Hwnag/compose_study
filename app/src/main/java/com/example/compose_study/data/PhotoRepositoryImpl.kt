package com.example.compose_study.data

import com.example.compose_study.domain.PhotoRepository
import com.example.compose_study.model.Photo
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val photoDataSource: PhotoDataSource
) : PhotoRepository {

    override suspend fun getPhotoList(page: Int, limit: Int): List<Photo> {
        return photoDataSource.getPhotoList(page = page, limit = limit)
    }
}
