package com.example.todolist.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.lang.IllegalStateException
import java.util.*
import java.util.concurrent.Executors

private  const val  DATABASE_NAME="todolist-database"
class TodoRepository private constructor(context: Context) {

    private val database: TodoDatabase = Room.databaseBuilder(
        context.applicationContext,
        TodoDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val todoDao = database.todoDao()
    private val executor = Executors.newSingleThreadExecutor()


    fun getAllTode(): LiveData<List<Todo>> = todoDao.getAllTodo()

    fun getTodo(id: UUID):LiveData<Todo?>{
        return todoDao.getTodo(id)
    }
    fun updataTodo(todo: Todo) {
        executor.execute {
            todoDao.updataTodo(todo)
        }
    }
    fun addTodo(todo: Todo){
        executor.execute {
            todoDao.addTodo(todo)
        }
    }

    fun deleteTodo (todo: Todo){
        executor.execute {
            todoDao.deleteTodo(todo)
        }
    }
    fun getDate(): LiveData<List<Todo>> = todoDao.getDate()

    companion object{

        var INSTANCE:TodoRepository?=null

        fun initialize(context: Context){
            if (INSTANCE==null){
                INSTANCE = TodoRepository(context)
            }
        }
        fun get(): TodoRepository{
            return INSTANCE?:
            throw IllegalStateException("ToDoList Repositor must be initialized")
        }
    }






















}






