package com.example.notekeeperapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: NoteDatabaseHelper
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = NoteDatabaseHelper(this)
        recyclerView = findViewById(R.id.recyclerViewNotes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        loadNotes()

        val fab: FloatingActionButton = findViewById(R.id.fabAdd)
        fab.setOnClickListener {
            startActivity(Intent(this, AddEditNoteActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        loadNotes()
    }

    private fun loadNotes() {
        val notes = dbHelper.getAllNotes()
        recyclerView.adapter = NoteAdapter(notes) { note ->
            val intent = Intent(this, AddEditNoteActivity::class.java)
            intent.putExtra("id", note.id)
            intent.putExtra("title", note.title)
            intent.putExtra("content", note.content)
            startActivity(intent)
        }
    }
}
