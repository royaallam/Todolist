package com.example.todolist.database

import androidx.room.TypeConverter
import java.util.*

class TodoTypeConverters {


    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return  date?.time

    }
    @TypeConverter
    fun toDate(millisSinceEpach:Long?): Date?{
        return  millisSinceEpach?.let { Date(it) }
    }
    @TypeConverter
    fun fromUUID(uuid: UUID?):String?{
        return uuid?.toString()
    }
    @TypeConverter
    fun toUUID(uuid:String?): UUID?{
        return UUID.fromString(uuid)
    }
}