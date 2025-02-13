package com.example.androidplayoground.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidplayoground.databinding.ItemRowBinding
import com.example.androidplayoground.model.Student


class StudentAdapter(val onClick :(Student)->Unit) : ListAdapter<Student,StudentAdapter.MyVH >(MyDiffUtil) {

    companion object {
        val MyDiffUtil = object : DiffUtil.ItemCallback<Student>(){
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean = oldItem == newItem
        }
    }

    inner class MyVH(private val binding: ItemRowBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:Student){
            binding.tvName.text = item.name
            binding.root.setOnLongClickListener{
                onClick(item)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        val inflater = LayoutInflater.from(parent.context)
        return MyVH(ItemRowBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {
        holder.bind(getItem(position))
    }
}