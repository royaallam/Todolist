package com.example.todolist.database

import android.app.Application

class TodolistlIntentApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        TodoRepository.initialize(this)
    }

}