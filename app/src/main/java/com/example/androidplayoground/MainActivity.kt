package com.example.androidplayoground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidplayoground.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val verticalItems = generateDummyData()
        val verticalAdapter = VerticalAdapter(verticalItems)

        binding.verticalRecyclerView.adapter = verticalAdapter


    }

    private fun generateDummyData(): List<VerticalItem> {
        return List(20) { index ->
            VerticalItem(
                id = index,
                title = "Vertical Item $index",
                horizontalItems = List(15) { hIndex ->
                    HorizontalItem(id = hIndex, title = "Item $hIndex")
                }
            )
        }
    }
}