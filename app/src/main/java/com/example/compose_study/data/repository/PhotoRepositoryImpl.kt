package com.example.compose_study.data.repository

import com.example.compose_study.data.datasource.PhotoDataSource
import com.example.compose_study.domain.PhotoRepository
import com.example.compose_study.model.Photo
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val photoDataSource: PhotoDataSource
) : PhotoRepository {

    override suspend fun getPhotoList(page: Int, limit: Int): List<Photo> {
        return photoDataSource.getPhotoList(page = page, limit = limit)
    }

    override suspend fun getPhoto(id: String): Photo {
        return photoDataSource.getPhoto(id = id.toInt())
    }
}
