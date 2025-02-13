package com.example.androidplayoground

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.androidplayoground.adapters.StudentAdapter
import com.example.androidplayoground.databinding.ActivityMainBinding
import com.example.androidplayoground.model.Student
import com.example.androidplayoground.viewmodels.StudentViewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: StudentViewModel by viewModels()
    private lateinit var adapter : StudentAdapter

    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        adapter = StudentAdapter { student ->
            viewModel.deleteStudent(student)
        }

        binding.mRv.adapter = adapter
        viewModel.allStudents.observe(this) {
            adapter.submitList(it)
        }

        binding.fab.setOnClickListener {
            id++
            viewModel.insertStudent(Student(id, "Student ${id}"))

        }

        setupSearchView()


    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.searchStudents(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.searchStudents(it)
                }
                return true
            }
        })

        viewModel.searchResults.observe(this){
            adapter.submitList(it)
        }
    }


}