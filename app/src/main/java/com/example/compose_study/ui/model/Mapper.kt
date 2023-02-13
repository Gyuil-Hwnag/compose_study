package com.example.compose_study.ui.model

import com.example.compose_study.model.TodoEntity

fun Todo.toData(): TodoEntity {
    return TodoEntity(
        time =  this.time,
        title = this.title,
        body = this.body
    )
}

fun List<TodoEntity>.toDomain(): List<Todo> {
    return map { Todo(
        time = it.time,
        todoIdx = it.todoIdx,
        title = it.title,
        body = it.body,
        isChecked = it.isChecked
    ) }
}

fun TodoEntity.toDomain(): Todo {
    return Todo(
        time = this.time,
        todoIdx = this.todoIdx,
        title = this.title,
        body = this.body,
        isChecked = this.isChecked
    )
}
