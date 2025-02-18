package com.example.androidplayoground

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import androidx.room.OnConflictStrategy.Companion as OnConflictStrategy1


@Dao
interface ToDoDao {


    @Insert(onConflict = OnConflictStrategy1.REPLACE)
    suspend fun insertTask(task: ToDoTask)

    @Update
    suspend fun updateTask(task: ToDoTask)

    @Delete
    suspend fun deleteTask(task: ToDoTask)

    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getAllTask(): Flow<List<ToDoTask>>
}