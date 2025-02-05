package com.example.androidplayoground.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidplayoground.model.Student

class MyViewModel : ViewModel() {



    private val allStudents = listOf(
        Student(1, "Apple"), Student(2, "Banana"), Student(3, "Cherry"),
        Student(4, "Date"), Student(5, "Eggplant"), Student(6, "Fig"),
        Student(7, "Grapes"), Student(8, "Honeydew"), Student(9, "Kiwi")
    )

    private val _filteredStudents = MutableLiveData<List<Student>>(allStudents)
    val filteredStudents: LiveData<List<Student>> get() = _filteredStudents

    fun searchStudents(query: String){
        _filteredStudents.value = if (query.isEmpty()){
            allStudents
        } else {
            allStudents.filter { it.studentName.contains(query, ignoreCase = true)}
        }
    }
}