package com.example.compose_study.domain

import com.example.compose_study.model.Todo

interface TodoRepository {
    suspend fun createTodo(todo: Todo): Unit

    suspend fun getTodoList(): List<Todo>

    suspend fun getTodo(todoIdx: Int): Todo

    suspend fun deleteTodo(todoIdx: Int): Unit

    suspend fun deleteTodoList(): Unit

    suspend fun updatePostLocal(todoIdx: Int, title: String, body: String): Unit

    suspend fun isCheckTodo(todoIdx: Int): Unit
}
