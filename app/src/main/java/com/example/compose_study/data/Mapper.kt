package com.example.compose_study.data

import com.example.compose_study.model.Photo

@JvmName("toDomainPhotoResponse")
fun List<PhotoResponse>.toDomain(): List<Photo> {
    return map { Photo(
        id = it.id,
        author = it.author,
        width = it.width,
        height = it.height,
        url = it.url,
        download_url = it.download_url
    ) }
}
