package com.example.androidplayoground.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidplayoground.StudentDatabase
import com.example.androidplayoground.StudentRepository
import com.example.androidplayoground.model.Student
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: StudentRepository
    val allStudents: LiveData<List<Student>>

    private val _searchResults = MutableLiveData<List<Student>>()
    val searchResults: LiveData<List<Student>> get() = _searchResults

    init {
        val studentDao = StudentDatabase.getDatabase(application).studentDao()
        repository = StudentRepository(studentDao)
        allStudents = repository.allStudents
    }



    fun searchStudents(query: String) {
        repository.searchStudents(query).observeForever {
            _searchResults.postValue(it)
        }
    }

    fun insertStudent(student: Student) = viewModelScope.launch {
        repository.insertStudent(student)
    }

    fun deleteStudent(student: Student) = viewModelScope.launch {
        repository.deleteStudent(student)
    }
}
