package com.example.compose_study.ui.model

import java.util.*

data class Todo(
    var todoIdx: Int,
    val time: Date,
    val title: String,
    val body: String,
    var isChecked: Boolean = false
)
