package com.example.compose_study.utils

data class Contents<T>(
    val totalCount: Int,
    val hasNext: Boolean?,
    val content: List<T>
) {
    val size: Int
        get() = content.size

    val isNotEmpty: Boolean
        get() = totalCount > 0
}
