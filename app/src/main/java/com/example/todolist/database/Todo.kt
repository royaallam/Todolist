package com.example.todolist.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
   data class Todo (
    @PrimaryKey val id:UUID= UUID.randomUUID(),
                var title:String="" ,
                var description:String="",
                var dateStart: Date?=null,
                var dateEnd: Date=Date(),
                var isChecked:Boolean=false


                 )
