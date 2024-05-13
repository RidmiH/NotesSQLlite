package com.example.notessqllite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notessqllite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: NotesDatabaseHelper
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Initialize the database helper
        db = NotesDatabaseHelper(this)
        // Initialize the notes adapter with all notes from the database
        notesAdapter = NotesAdapter(db.getAllNotes(), this)

        // Set up RecyclerView with a LinearLayoutManager and the notes adapter
        binding.notesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.notesRecyclerView.adapter = notesAdapter

        // Set OnClickListener for the addButton to navigate to the AddNoteActivity
        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        // Refresh the data in the notes adapter when the activity resumes
        notesAdapter.refreshData(db.getAllNotes())
    }
}
