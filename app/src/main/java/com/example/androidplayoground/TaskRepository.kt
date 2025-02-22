package com.example.androidplayoground

import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {

    fun getAllTasks() : Flow<List<Task>> = taskDao.getAllTask()

    fun searchTask(completed : Boolean) = taskDao.searchTask(completed)

    suspend fun insertTask(task: Task) = taskDao.insertTask(task)
    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)
    suspend fun updateTask(task: Task) = taskDao.updateTask(task)
}