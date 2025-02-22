package com.example.androidplayoground

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidplayoground.databinding.ItemTaskBinding

class TaskAdapter(
    private val onUpdateClick: (Task) -> Unit,
    private val onDeleteClick: (Task) -> Unit
) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffUtil) {

    companion object {
        val TaskDiffUtil = object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean =
                oldItem == newItem

        }
    }

    inner class TaskViewHolder(val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.apply {
                taskTitleTextView.text = task.title
                taskDescriptionTextView.text = task.description
                taskStatusTextView.text =
                    if (task.isCompleted) "Status : Completed" else "Status : Incomplete"
                taskStatusTextView.setTextColor(
                    if (task.isCompleted) ContextCompat.getColor(binding.root.context, android.R.color.holo_green_dark)
                    else ContextCompat.getColor(binding.root.context, android.R.color.holo_red_dark)
                )

                binding.updateButton.setOnClickListener { onUpdateClick(task) }
                binding.deleteButton.setOnClickListener { onDeleteClick(task) }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TaskViewHolder(ItemTaskBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) =
        holder.bind(getItem(position))


}