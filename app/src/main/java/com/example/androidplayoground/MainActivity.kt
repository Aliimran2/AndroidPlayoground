package com.example.androidplayoground

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import com.example.androidplayoground.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var adapter: TaskAdapter
    private val viewModel: TaskViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        adapter = TaskAdapter(
            onUpdateClick = { task -> viewModel.updateTask(task) },
            onDeleteClick = { task -> viewModel.deleteTask(task) }
        )

        binding.taskRecyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.searchResults.collectLatest { tasksList ->
                adapter.submitList(tasksList)
            }
        }

        binding.addButton.setOnClickListener {
            val title = binding.taskTitleEditText.text.toString().trim()
            val description = binding.taskDescriptionEditText.text.toString().trim()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                viewModel.insertTask(title, description)

                binding.apply {
                    taskTitleEditText.text.clear()
                    taskDescriptionEditText.text.clear()
                }
            }
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.updateSearch(newText?:"")
                return true
            }
        })


    }
}