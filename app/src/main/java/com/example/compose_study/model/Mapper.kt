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

