package com.example.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.*

@Dao
interface TodoDao {
    @Query("SELECT*FROM Todo")
    fun getAllTodo():LiveData<List<Todo>>

    @Query("SELECT*FROM Todo WHERE id=(:id)")
    fun getTodo(id:UUID):LiveData<Todo>

    @Update
    fun updataTodo(todo: Todo)
    @Insert
    fun addTodo(todo: Todo)
}