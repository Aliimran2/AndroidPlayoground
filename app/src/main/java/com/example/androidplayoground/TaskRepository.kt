package com.example.androidplayoground

import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {


    fun searchTask(query : String) : Flow<List<Task>> {
        return if (query.isEmpty()){
            taskDao.getAllTask()
        } else {
            taskDao.searchTask("%$query%")
        }
    }

    suspend fun insertTask(task: Task) = taskDao.insertTask(task)
    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)
    suspend fun updateTask(task: Task) = taskDao.updateTask(task)
}