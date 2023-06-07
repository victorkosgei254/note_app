package com.example.note_app.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.note_app.models.AppDatabase;
import com.example.note_app.models.Note;
import com.example.note_app.models.NoteDao;

import java.util.ArrayList;
import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private List<Note> allNotes;

    private NoteWorkManager workManager;
    MutableLiveData<List<Note>> notes = new MutableLiveData<>();
    public NoteRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        noteDao = database.noteDao();
//        allNotes = noteDao.getAllNotes();

        workManager = NoteWorkManager.getInstance();
        workManager.start();

    }

    public void insertNote(Note note)
    {
       workManager.postJob(() -> noteDao.insertNote(note));
    }
    public void updateNote(Note note)
    {
        workManager.postJob(()->{
            noteDao.updateNote(note);
        });

    }
    public void deleteNote(Note note)
    {
       workManager.postJob(()->{
           noteDao.deleteNote(note);
       });
    }
    public void deleteAllNotes()
    {
       workManager.postJob(()->{
           noteDao.deleteAllNotes();
       });
    }

    public LiveData<List<Note>> getAllNotes()
    {
        return noteDao.getAllNotes();
    }


}
