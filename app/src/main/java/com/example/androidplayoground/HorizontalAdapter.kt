package com.example.androidplayoground

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidplayoground.databinding.ItemHorizontalBinding


//child
class HorizontalAdapter(
    private val items : List<HorizontalItem>
) : RecyclerView.Adapter<HorizontalAdapter.HorizontalViewHolder>(){


    inner class HorizontalViewHolder(private val binding : ItemHorizontalBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : HorizontalItem){
            binding.horizontalItem = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {
        val binding = ItemHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HorizontalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}