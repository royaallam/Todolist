package com.example.todolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.todolist.database.Todo
import com.example.todolist.database.TodoRepository
import java.util.*

class TodoAFragmentViewModel:ViewModel() {

    private val todoRepository = TodoRepository.get()

    private val todoIdLiveData = MutableLiveData<UUID>()

    var todoLiveData: LiveData<Todo?> =

        Transformations.switchMap(todoIdLiveData) {

            todoRepository.getTodo(it)
        }

    fun loadTodo(todoId: UUID) {
        todoIdLiveData.value = todoId
    }

    fun saveUpdata(todo: Todo) {
        todoRepository.updataTodo(todo)
    }

    fun addTodo(todo: Todo) {
        todoRepository.addTodo(todo)
    }

}
