package com.vvit.stickynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

public class NotesMain extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;
    List<NoteModel> noteModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_main);
        setTitle("Notes");

        recyclerView = findViewById(R.id.id_addRecyclerView);

        NoteDatabase noteDatabase = new NoteDatabase(this);
        noteModelList = noteDatabase.getNote();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(this,noteModelList);
        recyclerView.setAdapter(adapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.add_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.id_add);
        Intent i = new Intent(NotesMain.this,AddNoteActivity.class);
        startActivity(i);
        return super.onOptionsItemSelected(item);
    }
}