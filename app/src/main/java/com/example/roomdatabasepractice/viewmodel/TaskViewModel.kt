package com.example.roomdatabasepractice.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdatabasepractice.database.TaskDao
import com.example.roomdatabasepractice.entity.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskDao: TaskDao
) : ViewModel() {

    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()
    fun insert(task: Task) {
        viewModelScope.launch {
            taskDao.insert(task)
        }
    }
    fun update(task: Task) {
        viewModelScope.launch {
            taskDao.update(task)
        }
    }
    fun delete(task: Task) {
        viewModelScope.launch {
            taskDao.delete(task)
        }
    }
}