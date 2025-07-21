package com.example.roomdatabasepractice

import android.content.Context
import androidx.room.Room
import com.example.roomdatabasepractice.database.TaskDao
import com.example.roomdatabasepractice.database.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): TaskDatabase {
        return Room.databaseBuilder(
            appContext,
            TaskDatabase::class.java,
            "your_database_name" // Replace with your actual database name
        ).build()
    }

    @Provides
    fun provideTaskDao(appDatabase: TaskDatabase): TaskDao {
        return appDatabase.taskDao() // Assuming your AppDatabase has a method like `fun taskDao(): TaskDao`
    }
}