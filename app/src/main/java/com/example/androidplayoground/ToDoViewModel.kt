package com.example.androidplayoground

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ToDoViewModel(private val repository: ToDoRepository) : ViewModel() {


    private val _dataFlow = MutableStateFlow("Hello Flow!")
    val dataFlow = _dataFlow.asStateFlow()
    val dataFlow2: StateFlow<String> = _dataFlow

    fun updateData(newData : String){
        _dataFlow.value = newData
    }


    //manage ui state we use StateFlow
    private val _tasks = MutableStateFlow<List<ToDoTask>>(emptyList())
    val tasks: StateFlow<List<ToDoTask>> = _tasks.asStateFlow()

    //"SharedFlow for one time UI Events like Toast message
    private val _eventFlow = MutableSharedFlow<String>()
    val eventFlow: SharedFlow<String> = _eventFlow.asSharedFlow()


    init {

        viewModelScope.launch {
            repository.allTasks.collect { taskList: List<ToDoTask> ->
                _tasks.value = taskList

            }
        }
    }

    fun addTask(title: String) {
        if (title.isBlank()) return
        viewModelScope.launch {
            repository.insertTask(ToDoTask(title = title))
            _eventFlow.emit("Task Added Successfully")
        }
    }

    fun deleteTask(task: ToDoTask) {
        viewModelScope.launch {
            repository.deleteTask(task)
            _eventFlow.emit("Task Deleted Successfully")
        }
    }

    fun updateTask(task: ToDoTask) {
        viewModelScope.launch {
            repository.updateTask(task)
            _eventFlow.emit("Task updated Successfully")
        }
    }

    fun toggleTaskCompletion(task: ToDoTask){
        viewModelScope.launch {
            repository.updateTask(task.copy(isCompleted = !task.isCompleted))
        }
    }
}

class ToDoViewModelFactory(private val repository: ToDoRepository) : ViewModelProvider.Factory {

    // This method will return the instance of the ViewModel
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Check if the requested ViewModel is ToDoViewModel
        if (modelClass.isAssignableFrom(ToDoViewModel::class.java)) {
            return ToDoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}