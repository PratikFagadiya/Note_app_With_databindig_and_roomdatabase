package com.pratik.noteappdatabindigandroomdatabase.database;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.pratik.noteappdatabindigandroomdatabase.models.Note;

import java.util.List;


public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> mNotes;

    public NoteRepository(Application application) {
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        mNotes = noteDao.getNoteList();
    }

    public LiveData<List<Note>> getAllNotes() {
        return mNotes;
    }

    public void insertNote(Note note) {
        NoteDatabase.databaseWriteExecutor.execute(() -> noteDao.insertNote(note));
    }

}