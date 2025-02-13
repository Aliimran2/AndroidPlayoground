package com.example.androidplayoground

import androidx.lifecycle.LiveData
import com.example.androidplayoground.model.Student

class StudentRepository(private val studentDao: StudentDao) {
    val allStudents: LiveData<List<Student>> = studentDao.getAllStudents()

    fun searchStudents(query: String): LiveData<List<Student>> {
        return studentDao.searchStudents("%$query%")
    }

    suspend fun insertStudent(student: Student) {
        studentDao.insertStudent(student)
    }

    suspend fun deleteStudent(student: Student){
        studentDao.deleteStudent(student)
    }
}