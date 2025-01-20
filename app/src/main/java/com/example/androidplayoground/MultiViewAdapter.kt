package com.example.androidplayoground

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidplayoground.databinding.ItemContentBinding
import com.example.androidplayoground.databinding.ItemHeaderBinding

class MultiViewAdapter : ListAdapter<Item, RecyclerView.ViewHolder>(DIFF_UTIL) {


    companion object {

        const val TYPE_HEADER = 0
        const val TYPE_CONTENT = 1

        val DIFF_UTIL = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 3 == 0) TYPE_HEADER else TYPE_CONTENT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_HEADER -> {
                val binding = ItemHeaderBinding.inflate(inflater, parent, false)
                HeaderViewHolder(binding)
            }

            TYPE_CONTENT -> {
                val binding = ItemContentBinding.inflate(inflater, parent, false)
                ContentViewHolder(binding)
            }

            else -> throw IllegalArgumentException("invalid")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is HeaderViewHolder -> holder.bind(item)
            is ContentViewHolder -> holder.bind(item)
        }

    }

    class HeaderViewHolder(private val binding: ItemHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    class ContentViewHolder(private val binding: ItemContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}