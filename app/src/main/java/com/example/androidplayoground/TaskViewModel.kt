package com.example.androidplayoground

import android.app.ActivityManager.TaskDescription
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks = _tasks.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllTasks().collect {taskList ->
                _tasks.value = taskList
            }
        }
    }


    fun insertTask(title: String, description: String) {
        val newTask = Task(
            title = title,
            description = description,
        )
        viewModelScope.launch {
            repository.insertTask(newTask)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun updateTask(task: Task) {
        val updateTask = task.copy(isCompleted = !task.isCompleted)
        viewModelScope.launch {
            repository.updateTask(updateTask)
        }
    }

}

class TaskViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}