package com.example.androidplayoground

import kotlinx.coroutines.flow.Flow

class ToDoRepository(private val dao:ToDoDao) {
    val allTasks: Flow<List<ToDoTask>> = dao.getAllTask()

    suspend fun insertTask(task: ToDoTask) = dao.insertTask(task)
    suspend fun deleteTask(task: ToDoTask) = dao.deleteTask(task)
    suspend fun updateTask(task: ToDoTask) = dao.updateTask(task)
}