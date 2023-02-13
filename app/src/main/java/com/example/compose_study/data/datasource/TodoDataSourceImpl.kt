package com.example.compose_study.data.datasource

import com.example.compose_study.data.local.TodoDao
import com.example.compose_study.model.Todo
import com.example.compose_study.model.TodoEntity
import com.example.compose_study.model.toDomain
import javax.inject.Inject

class TodoDataSourceImpl @Inject constructor(
    private val todoDao: TodoDao
) : TodoDataSource {

    override suspend fun createTodo(todo: TodoEntity): Unit {
        return todoDao.createTodo(todo)
    }

    override suspend fun getTodoList(): List<Todo> {
        return todoDao.getTodoList().toDomain()
    }

    override suspend fun getTodo(todoIdx: Int): Todo {
        return todoDao.getTodo(todoIdx).toDomain()
    }

    override suspend fun deleteTodo(todoIdx: Int): Unit {
        return todoDao.deleteTodo(todoIdx)
    }

    override suspend fun deleteTodoList(): Unit {
        return todoDao.deleteTodoList()
    }

    override suspend fun updatePostLocal(todoIdx: Int, title: String, body: String): Unit {
        return  todoDao.patchTodo(todoIdx, title, body)
    }

    override suspend fun isCheckTodo(todoIdx: Int): Unit {
        return todoDao.isCheckTodo(todoIdx)
    }
}
