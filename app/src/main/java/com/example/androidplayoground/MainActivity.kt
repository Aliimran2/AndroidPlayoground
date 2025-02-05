package com.example.androidplayoground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidplayoground.adapters.MyAdapter
import com.example.androidplayoground.databinding.ActivityMainBinding
import com.example.androidplayoground.model.Student

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var adapter: MyAdapter

    private val myList = List(20){ index ->
        Student(index, "Student $index")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)




        initRecyclerView()



    }

    private fun initRecyclerView() {
        adapter = MyAdapter()
        adapter.submitList(myList)
        binding.mRv.adapter = adapter
    }
}