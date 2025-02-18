package com.example.androidplayoground

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidplayoground.databinding.ItemTaskBinding

class ToDoAdapter(private val viewModel: ToDoViewModel) :
    RecyclerView.Adapter<ToDoAdapter.TaskViewHolder>() {

    private var taskList: List<ToDoTask> = emptyList()

    fun submitList(list: List<ToDoTask>) {
        taskList = list
        notifyDataSetChanged()
    }

    class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.binding.taskTitle.text = task.title
        holder.binding.taskCheckBox.isChecked = task.isCompleted

        holder.binding.taskCheckBox.setOnClickListener {
            viewModel.toggleTaskCompletion(task)
        }

        holder.binding.deleteButton.setOnClickListener {
            viewModel.deleteTask(task)
        }
    }

    override fun getItemCount() = taskList.size
}


