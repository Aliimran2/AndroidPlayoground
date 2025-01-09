package com.example.androidplayoground

import org.junit.Assert.*

import org.junit.Test

class MyUtilsTest {

    @Test
    fun add() {
        val result = MyUtils.add(2,4)
        assertEquals(6, result)


    }

    @Test
    fun capitalize() {
        val result = MyUtils.capitalize("hello")
        assertEquals("Hello", result)
    }

    @Test
    fun testCapitalizeWithNull() {
        val result = MyUtils.capitalize(null)
        assertEquals(null, null)
    }
}