package com.example.compose_study.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageItem(
    val imagePath: String,
    val imageId: Long
) : Parcelable

data class ImageBucket(
    val name: String,
    val id: Long,
    val count: Int
)
