package com.example.androidplayoground.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel() {

    private val _snackBarMsg = MutableSharedFlow<String>()
    val snackBarMsg = _snackBarMsg.asSharedFlow()



    private val _searchQuery = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val searchResults = _searchQuery
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest {query ->
            repository.searchTask(query)
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun updateSearch(query :String){
        _searchQuery.value = query
    }






    fun insertTask(title: String, description: String) {
        val newTask = Task(
            title = title,
            description = description,
        )
        viewModelScope.launch(Dispatchers.IO) {

            repository.insertTask(newTask)
            _snackBarMsg.emit("$title is saved successfully")
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
            _snackBarMsg.emit("${task.title} is deleted successfully")
        }
    }

    fun updateTask(task: Task) {
        val updateTask = task.copy(isCompleted = !task.isCompleted)
        viewModelScope.launch {
            repository.updateTask(updateTask)
        }
    }

}

