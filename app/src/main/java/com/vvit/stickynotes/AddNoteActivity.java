package com.vvit.stickynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddNoteActivity extends AppCompatActivity {

    EditText title,details;
    Button addNoteBtn;
    String todayDate,currentTime;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        getSupportActionBar().setTitle("Add New Note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title =findViewById(R.id.id_addNote);
        details = findViewById(R.id.id_noteDetails);
        addNoteBtn = findViewById(R.id.id_addNoteBtn);

        calendar = Calendar.getInstance();
        todayDate = calendar.get(Calendar.YEAR)+"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.DAY_OF_MONTH);
        currentTime = pad(calendar.get(Calendar.HOUR))+":"+pad(calendar.get(Calendar.MINUTE));
        Log.d("calendar","Date and Time"+todayDate+" and "+currentTime);

        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NoteModel noteModel=new NoteModel(title.getText().toString(),details.getText().toString(),todayDate,currentTime);
                NoteDatabase db=new NoteDatabase(AddNoteActivity.this);
                db.AddNote(noteModel);

                Intent intent=new Intent(AddNoteActivity.this,MainActivity.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(),"Note saved", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public String pad(int i){
        if(i<0)
            return "0"+i;
        return String.valueOf(i);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
