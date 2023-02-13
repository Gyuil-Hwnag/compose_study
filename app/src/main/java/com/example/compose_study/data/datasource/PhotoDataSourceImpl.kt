package com.example.compose_study.data.datasource
import com.example.compose_study.data.api.PhotoApi
import com.example.compose_study.model.toDomain
import com.example.compose_study.model.Photo
import javax.inject.Inject

class PhotoDataSourceImpl @Inject constructor(
    private val photoApi: PhotoApi
    ) : PhotoDataSource {

    override suspend fun getPhotoList(page: Int, limit: Int): List<Photo> {
        return photoApi.getPhotoList(page = page, limit = limit).toDomain()
    }

    override suspend fun getPhoto(id: Int): Photo {
        return photoApi.getPhoto(id = id).toDomain()
    }
}
