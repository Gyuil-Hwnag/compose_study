package com.example.compose_study.model

import java.util.Date

data class Todo(
    val time: String,
    var todoIdx: Int,
    val title: String,
    val body: String,
    var isChecked: Boolean = false
)
