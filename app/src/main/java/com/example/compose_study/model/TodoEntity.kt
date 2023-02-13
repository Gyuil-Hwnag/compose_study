package com.example.compose_study.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class TodoEntity(
    val time: String,
    val title: String,
    val body: String,
    var isChecked: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    var todoIdx: Int = 0
}
