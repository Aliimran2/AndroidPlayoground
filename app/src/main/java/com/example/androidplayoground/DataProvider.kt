package com.example.androidplayoground

// DataProvider.kt
object DataProvider {

    // Mocking a data source for now. In a real app, this could be an API or a database.
    private val itemList = listOf(
        Item(1, "Item 1", "Description for Item 1"),
        Item(2, "Item 2", "Description for Item 2"),
        Item(3, "Item 3", "Description for Item 3"),
        Item(4, "Item 4", "Description for Item 4"),
        Item(5, "Item 5", "Description for Item 5")
    )

    // A function to get the data (can be extended to handle remote requests or database queries)
    fun getItems(): List<Item> {
        // In a real-world scenario, data fetching would be async and handle errors.
        return itemList
    }
}
