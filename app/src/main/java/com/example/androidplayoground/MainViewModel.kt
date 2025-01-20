package com.example.androidplayoground

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> get() = _items

    init {
        loadItems()
    }

    private fun loadItems() {
        _items.value = List(20) {
            Item(it, "Item# $it")
        }
    }
}