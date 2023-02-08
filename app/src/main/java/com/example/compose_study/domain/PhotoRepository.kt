package com.example.compose_study.domain

import com.example.compose_study.model.Photo

interface PhotoRepository {
    suspend fun getPhotoList(page: Int, limit: Int): List<Photo>

    suspend fun getPhoto(id: String): Photo
}
