package com.example.androidplayoground

import kotlinx.coroutines.*

fun main() = runBlocking {


    println("Main program starts: ${Thread.currentThread().name}")

    val job : Job = launch {
        for (i in 0.. 500){
            println("$i.")
            delay(50)
        }
    }
    delay(20)
    job.cancel()
    job.join()



    println("Main program ends: ${Thread.currentThread().name}")
}

suspend fun mySuspend(time : Long){
    delay(time)
}
