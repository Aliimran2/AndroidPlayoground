package com.example.androidplayoground

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class StorageViewModel(application: Application): AndroidViewModel(application) {
    private val repository = StorageRepository(application)

    private val _savedData = MutableLiveData<String?>()
    val savedData : LiveData<String?> = _savedData

    private val _statusMsg = MutableLiveData<String>()
    val statusMsg : LiveData<String> = _statusMsg

    fun saveText(data: String){
        if (repository.writeToFile(data)){
            _statusMsg.value = "Data written successfully"
        } else {
            _statusMsg.value = "Failed to write"
        }
    }

    fun readText(){
        _savedData.value = repository.readFromFile()
    }

    fun deleteFile() {
        if (repository.deleteFile()){
            _statusMsg.value = "file deleted successfully"
        } else {
            _statusMsg.value = "failed to delete"
        }
    }
}