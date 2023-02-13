package com.example.compose_study.data.datasource

import com.example.compose_study.model.Photo

interface PhotoDataSource {
    suspend fun getPhotoList(page: Int, limit: Int): List<Photo>

    suspend fun getPhoto(id: Int): Photo
}
