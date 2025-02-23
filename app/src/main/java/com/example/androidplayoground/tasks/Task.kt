package com.example.androidplayoground.tasks

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String,
    val timestamp: Long = System.currentTimeMillis(),
    val isCompleted: Boolean = false
)
