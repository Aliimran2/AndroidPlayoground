package com.example.androidplayoground

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.androidplayoground.adapters.MyAdapter
import com.example.androidplayoground.databinding.ActivityMainBinding
import com.example.androidplayoground.model.Student
import com.example.androidplayoground.viewmodels.MyViewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MyViewModel by viewModels()

    private lateinit var adapter: MyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)




        initRecyclerView()
        setupSearchBar()
        observeViewModel()


    }


    private fun setupSearchBar() {
        binding.searchBar.setOnClickListener {
            binding.searchView.show()
        }
        binding.searchView.editText.setOnEditorActionListener { v, actionId, event ->
            val query = v.text.toString()
            viewModel.searchStudents(query)


            binding.searchView.hide()
            true

        }
    }

    private fun observeViewModel() {
        viewModel.filteredStudents.observe(this) {students->
            Log.d("MainActivity", "Students list: $students") // ✅ Debugging log
            adapter.submitList(students)
        }
    }

    private fun initRecyclerView() {
        adapter = MyAdapter()
        binding.mRv.adapter = adapter
    }
}