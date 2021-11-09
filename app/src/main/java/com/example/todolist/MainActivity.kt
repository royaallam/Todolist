package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolist.Fragmenttodo.FragmentListAdd

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentFragment=supportFragmentManager
            .findFragmentById(R.id.fragment_Container)
        if(currentFragment==null){
            val fragment= Todolist()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_Container,fragment)
                .commit()
        }
    }
}