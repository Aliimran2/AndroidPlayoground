package com.example.androidplayoground

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// MyItemViewModel.kt
class MyViewModel : ViewModel() {

    // LiveData to hold the list of items
    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> get() = _items

    init {
        // Fetch items from the DataProvider
        fetchItems()
    }

    private fun fetchItems() {
        // Simulate a network request or database query (you could replace this with an actual async task)
        _items.value = DataProvider.getItems()
    }
}
