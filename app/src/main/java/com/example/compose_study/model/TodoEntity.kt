package com.example.compose_study.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "todo")
data class TodoEntity(
    val time: Date,
    val title: String,
    val body: String,
    var isChecked: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    var todoIdx: Int = 0
}
