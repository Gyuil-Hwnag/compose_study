package com.example.compose_study.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.compose_study.model.TodoEntity

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createTodo(todo: TodoEntity)

    @Query("SELECT * FROM todo ORDER BY todoIdx DESC")
    fun getTodoList(): List<TodoEntity>

    @Query("SELECT * FROM todo WHERE todoIdx = :idx ")
    fun getTodo(idx: Int): TodoEntity

    @Query("DELETE FROM todo WHERE todoIdx = :idx")
    fun deleteTodo(idx: Int)

    @Query("DELETE FROM todo")
    fun deleteTodoList()

    @Query("UPDATE todo SET title = :title, body = :body WHERE todoIdx = :id")
    fun patchTodo(id: Int, title: String, body: String)

    @Query("UPDATE todo SET isChecked = not isChecked  WHERE todoIdx = :id")
    fun isCheckTodo(id: Int)
}
