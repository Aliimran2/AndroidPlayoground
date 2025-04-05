package com.example.androidplayoground

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ViewModelPlayGround() : ViewModel() {

    private val _counterFlow = MutableStateFlow(0)
    val counterFlow = _counterFlow.asStateFlow()

    private val _downloadProgress = MutableSharedFlow<Int>(replay = 1)
    val downloadProgress = _downloadProgress.asSharedFlow()


    fun startDownload() {
        viewModelScope.launch {
            for (i in 1..100){
                delay(100)
                _downloadProgress.emit(i)
            }
        }
    }


    init {
        viewModelScope.launch {
            while (true) {
                delay(1000)
                _counterFlow.value += 1
            }
        }
    }
}