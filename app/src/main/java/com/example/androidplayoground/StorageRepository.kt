package com.example.androidplayoground

import android.content.Context
import java.io.File

class StorageRepository(private val context: Context) {
    private val fileName = "saved_data.txt"

    //write
    fun writeToFile(content: String): Boolean {
        return try {
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
                it.write(content.toByteArray())
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    //read

    fun readFromFile(): String? {
        return try {
            context.openFileInput(fileName).bufferedReader().use { it.readLine() }

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    //delete
    fun deleteFile() : Boolean {
        return try {
            val file = File(context.filesDir, fileName)
            file.delete()
        } catch (e:Exception){
            e.printStackTrace()
            false
        }
    }
}