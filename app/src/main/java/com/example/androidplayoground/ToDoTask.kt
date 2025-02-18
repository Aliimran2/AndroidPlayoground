package com.example.androidplayoground

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class ToDoTask(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val isCompleted: Boolean = false
)