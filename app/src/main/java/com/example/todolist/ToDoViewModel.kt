package com.example.todolist

import androidx.lifecycle.ViewModel

import com.example.todolist.Fragmenttodo.Tododate
import com.example.todolist.database.Todo
import com.example.todolist.database.TodoRepository

class ToDoViewModel:ViewModel() {
    val todoRepository=TodoRepository.get()

    val LiveDataTodo=todoRepository.getAllTode()


    fun addTodo(todo: Todo){
        todoRepository.addTodo(todo)
    }
}