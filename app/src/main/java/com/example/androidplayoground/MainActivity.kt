package com.example.androidplayoground

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.androidplayoground.databinding.ActivitySecondBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MIAS_MainActivity"
    }

    private val binding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }

    private lateinit var repository: ToDoRepository
    private lateinit var dao : ToDoDao

    private lateinit var adapter: ToDoAdapter


    private val viewModel: ToDoViewModel by viewModels {
        val repository = ToDoRepository(dao)
        ToDoViewModelFactory(repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        dao = ToDoDatabase.getDatabase(this).toDoDao()

        adapter = ToDoAdapter(viewModel)
        binding.recyclerView.adapter = adapter



//        lifecycleScope.launch {
//            viewModel.tasks.collect{toDoList: List<ToDoTask> ->
//                adapter.submitList(toDoList)
//            }
//        }

//        lifecycleScope.launch {
//            viewModel.eventFlow.collect {message ->
//
//                Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
//
//            }
//        }

//        binding.addTaskBtn.setOnClickListener{
//            val taskTitle = binding.taskInput.text.toString()
//            viewModel.addTask(taskTitle)
//            binding.taskInput.text?.clear()
//        }



        lifecycleScope.launch {
            viewModel.dataFlow.collect {
                Log.d("MIAS_MainActivity", "$it")
            }
        }



    }

    private fun collectNumbersFlow() {
        CoroutineScope(Dispatchers.Main).launch {
            transformedFlow()
                .collect {value ->
                Log.d("MIAS_MainActivity", "Received : $value")
            }
        }
    }

    private fun numbersFlow() : Flow<Int> = flow {
        for (i in 1.. 10){

            emit(i)
            delay(1000)
        }
    }

    private fun transformedFlow() : Flow<String> {
        return numbersFlow()
            .map { number -> "Number : $number" }
            .filter { numString -> numString.contains("2").not() }
    }

}