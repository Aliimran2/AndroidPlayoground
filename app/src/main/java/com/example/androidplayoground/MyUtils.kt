package com.example.androidplayoground

object MyUtils {
    fun add(a : Int, b : Int) : Int{
        return a + b
    }

    fun capitalize(input: String?) : String {
        return input?.let {
            if(input.isNotEmpty()) it[0].uppercase() + it.substring(1) else it
        } ?: ""
    }
}