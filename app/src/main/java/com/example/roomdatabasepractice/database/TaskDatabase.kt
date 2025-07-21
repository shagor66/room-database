package com.example.roomdatabasepractice.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomdatabasepractice.entity.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}