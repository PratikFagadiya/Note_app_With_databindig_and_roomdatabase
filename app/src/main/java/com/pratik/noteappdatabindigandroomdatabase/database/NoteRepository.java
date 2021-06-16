package com.pratik.noteappdatabindigandroomdatabase.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.pratik.noteappdatabindigandroomdatabase.models.Note;

import java.util.List;

public class NoteRepository {

    NoteDao noteDao;
    LiveData<List<Note>> mNotes;
    private static NoteRepository noteRepository;

    public static NoteRepository getNoteRepository(Application application) {
        if (noteRepository == null) {
            noteRepository = new NoteRepository(application);
        }
        return noteRepository;
    }

    public NoteRepository(Application application) {
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        mNotes = noteDao.getNoteList();
//        Log.i("TAG_123", "NoteRepository: " + mNotes.getValue());

//        for (int i = 0; i <mNotes.getValue(). ; i++) {
//
//        }

//        Log.i("TAG_123", "NoteRepository: " + mNotes.observe().toString());
    }

    public LiveData<List<Note>> getAllNotes() {
        return mNotes;
    }

    public void insertNote(Note note) {
        NoteDatabase.databaseWriteExecutor.execute(() -> noteDao.insertNote(note));
    }

    public void updateNote(Note note) {
//        new Thread(() -> noteDao.updateNote(note.getNote(), note.getId())).start();
        new Thread(() -> noteDao.updateNote(note)).start();
    }

    public void deleteNote(Note note) {
        new Thread(() -> noteDao.deleteNote(note)).start();
    }
}