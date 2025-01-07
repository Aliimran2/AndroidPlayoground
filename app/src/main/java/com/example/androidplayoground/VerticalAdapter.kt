package com.example.androidplayoground

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidplayoground.databinding.ItemVerticalBinding

class VerticalAdapter(
    private val items : List<VerticalItem>
) : RecyclerView.Adapter<VerticalAdapter.VerticalViewHolder>(){

    class VerticalViewHolder(private val binding : ItemVerticalBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : VerticalItem){
            binding.verticalItem = item


            val horizontalAdapter = HorizontalAdapter(item.horizontalItems)
            binding.horizontalRecyclerView.apply {
                layoutManager = LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
                adapter = horizontalAdapter
            }

            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalViewHolder {
        val binding = ItemVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VerticalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VerticalViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}