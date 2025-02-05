package com.example.androidplayoground

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.androidplayoground.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel : StorageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            viewModel.savedData.observe(this@MainActivity) {
                textViewSavedData.text = it
            }

            viewModel.statusMsg.observe(this@MainActivity){
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            }

            buttonSave.setOnClickListener {
                val data = editTextData.text.toString()
                if (data.isNotEmpty()){
                    viewModel.saveText(data)
                } else {
                    Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                }
            }

            buttonLoad.setOnClickListener {
                viewModel.readText()
            }

            buttonDelete.setOnClickListener {
                viewModel.deleteFile()
            }

        }



    }
}