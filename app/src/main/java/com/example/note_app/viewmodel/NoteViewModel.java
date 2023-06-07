package com.example.note_app.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.note_app.models.Note;
import com.example.note_app.repositories.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        this.repository = new NoteRepository(application);
        this.allNotes = repository.getAllNotes();

    }

    //Wrapper methods

    public void insertNote(Note note)
    {
        repository.insertNote(note);
    }

    public void updateNote(Note note)
    {
        repository.updateNote(note);
    }

    public void deleteNote(Note note)
    {
        repository.deleteNote(note);
    }

    public void deleteAllNotes()
    {
        repository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return repository.getAllNotes();
    }
}
