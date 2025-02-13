package com.example.androidplayoground

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidplayoground.model.Student

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("SELECT * FROM students ORDER BY name ASC")
    fun getAllStudents(): LiveData<List<Student>>

    @Query("SELECT * FROM students WHERE name LIKE :query")
    fun searchStudents(query: String): LiveData<List<Student>>
}
