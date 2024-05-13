package com.example.notessqllite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.notessqllite.databinding.ActivityAddNoteBinding


class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: NotesDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the database helper
        db = NotesDatabaseHelper(this)

        // Set OnClickListener for the saveButton
        binding.saveButton.setOnClickListener{
            // Get title and content from EditText fields
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()

            if(title.isEmpty()){
                binding.titleEditText.error = "Title content be empty"
                return@setOnClickListener
            }
            if (content.isEmpty()){
                binding.contentEditText.error  = "Content can not be empty"
                return@setOnClickListener
            }

            val note = Note(0,title,content)

            // Insert the note into the database
            db.insertNote(note)

            // Finish the activity and show a toast indicating that the note is saved
            finish()
            Toast.makeText(this,"Note Saved",Toast.LENGTH_SHORT).show()
        }


    }
}