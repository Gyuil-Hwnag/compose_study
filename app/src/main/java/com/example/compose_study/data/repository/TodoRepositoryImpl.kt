package com.example.compose_study.data.repository

import com.example.compose_study.data.datasource.TodoDataSource
import com.example.compose_study.domain.TodoRepository
import com.example.compose_study.model.Todo
import com.example.compose_study.model.toData
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val todoDataSource: TodoDataSource
) : TodoRepository {

    override suspend fun createTodo(todo: Todo): Unit {
        return todoDataSource.createTodo(todo.toData())
    }

    override suspend fun getTodoList(): List<Todo> {
        return todoDataSource.getTodoList()
    }

    override suspend fun getTodo(todoIdx: Int): Todo {
        return todoDataSource.getTodo(todoIdx)
    }


    override suspend fun deleteTodo(todoIdx: Int): Unit {
        return todoDataSource.deleteTodo(todoIdx)
    }

    override suspend fun deleteTodoList(): Unit {
        return todoDataSource.deleteTodoList()
    }


    override suspend fun updatePostLocal(todoIdx: Int, title: String, body: String): Unit {
        return todoDataSource.updatePostLocal(todoIdx, title, body)
    }

    override suspend fun isCheckTodo(todoIdx: Int): Unit {
        return todoDataSource.isCheckTodo(todoIdx)
    }
}
