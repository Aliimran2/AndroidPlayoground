package com.example.androidplayoground

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel(){

    val allNotes : LiveData<List<Note>> = repository.allNotes

    fun insert(note: Note){
        viewModelScope.launch {
            repository.insert(note)
        }
    }

    fun delete(note: Note){
        viewModelScope.launch {
            repository.delete(note)
        }
    }
}