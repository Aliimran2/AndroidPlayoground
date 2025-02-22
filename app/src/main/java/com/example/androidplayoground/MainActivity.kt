package com.example.androidplayoground

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.androidplayoground.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var viewModelFactory: TaskViewModelFactory
    private lateinit var adapter: TaskAdapter
    private val viewModel: TaskViewModel by viewModels { viewModelFactory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val taskDao = AppDatabase.getDatabase(this).taskDao()
        val repository = TaskRepository(taskDao)
        viewModelFactory = TaskViewModelFactory(repository)

        adapter = TaskAdapter(
            onUpdateClick = { task -> viewModel.updateTask(task) },
            onDeleteClick = { task -> viewModel.deleteTask(task) }
        )

        binding.taskRecyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.tasks.collectLatest { taskList ->
                adapter.submitList(taskList)
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


    }
}