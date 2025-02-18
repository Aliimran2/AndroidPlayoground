package com.example.androidplayoground

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val myFlow = flow {
        for (i in 1..5){
            emit(i)
            kotlinx.coroutines.delay(1000)
        }

    }
    myFlow.collect{value->
        println(value)
    }
}