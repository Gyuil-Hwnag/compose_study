package com.example.compose_study.model

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

fun PhotoResponse.toDomain(): Photo {
    return Photo(
        id = this.id,
        author = this.author,
        width = this.width,
        height = this.height,
        url = this.url,
        download_url = this.download_url
    )
}
