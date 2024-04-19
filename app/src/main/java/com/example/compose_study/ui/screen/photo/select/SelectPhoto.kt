package com.example.compose_study.ui.screen.photo.select

data class SelectPhoto(
    val id: Long,
    val imageUrl: String,
    var isChecked: Boolean = false
) {
    fun onPhotoChecked(checked: Boolean) {
        this.isChecked = checked
    }
}
