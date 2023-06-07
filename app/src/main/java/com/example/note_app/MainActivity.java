package com.example.note_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.note_app.viewmodel.NoteViewModel;
import com.example.note_app.views.NoteFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, new NoteFragment())
                .commit();

    }

}