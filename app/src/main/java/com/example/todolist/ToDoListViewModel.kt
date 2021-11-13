package com.example.todolist

import androidx.lifecycle.ViewModel

import com.example.todolist.database.Todo
import com.example.todolist.database.TodoRepository

class ToDoListViewModel:ViewModel() {
    val todoRepository = TodoRepository.get()

    val LiveDataTodo = todoRepository.getAllTode()

    fun deleteTodo(todo:Todo){
        todoRepository.deleteTodo(todo)
    }

    fun updateTodo(todo: Todo) {
        todoRepository.updataTodo(todo)
    }

    fun getDate(){
        todoRepository.getDate()
    }

}