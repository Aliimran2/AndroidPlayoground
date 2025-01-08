package com.example.androidplayoground

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidplayoground.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var noteAdapter: NoteAdapter

    private val noteViewModel: NoteViewModel by viewModels()

    var number = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        noteAdapter = NoteAdapter { note ->
            noteViewModel.delete(note)
        }


        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = noteAdapter
        }

        noteViewModel.allNotes.observe(this) { notes ->
            noteAdapter.setNotes(notes)
        }

        binding.addButton.setOnClickListener {
            newNumber()
            val newNote = Note(

                title = "New Note $number",
                content = "New Description $number"
            )

            noteViewModel.insert(newNote)
        }


    }

    private fun newNumber(): Int {
        number++
        return number

    }
}