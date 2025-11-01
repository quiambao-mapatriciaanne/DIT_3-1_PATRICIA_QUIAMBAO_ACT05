package com.example.notekeeperapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddEditNoteActivity : AppCompatActivity() {

    private lateinit var dbHelper: NoteDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)

        dbHelper = NoteDatabaseHelper(this)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etContent = findViewById<EditText>(R.id.etContent)
        val btnSave = findViewById<Button>(R.id.btnSave)

        val id = intent.getIntExtra("id", -1)
        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")

        if (id != -1) {
            etTitle.setText(title)
            etContent.setText(content)
        }

        btnSave.setOnClickListener {
            val newTitle = etTitle.text.toString()
            val newContent = etContent.text.toString()

            if (id == -1) {
                dbHelper.addNote(newTitle, newContent)
            } else {
                dbHelper.updateNote(id, newTitle, newContent)
            }
            finish()
        }
    }
}
