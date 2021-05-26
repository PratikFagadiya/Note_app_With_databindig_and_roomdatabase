package com.pratik.noteappdatabindigandroomdatabase.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.pratik.noteappdatabindigandroomdatabase.models.Note;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> mNotes;

    NoteRepository(Application application) {
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        mNotes = noteDao.getNoteList();
    }


    LiveData<List<Note>> getAllNotes() {
        return mNotes;
    }

    void insertNote(Note note) {
        NoteDatabase.databaseWriteExecutor.execute(() -> noteDao.insertNote(note));
    }

}
