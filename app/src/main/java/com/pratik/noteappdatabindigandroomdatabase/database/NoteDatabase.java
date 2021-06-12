package com.pratik.noteappdatabindigandroomdatabase.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.pratik.noteappdatabindigandroomdatabase.models.Note;
import com.pratik.noteappdatabindigandroomdatabase.utils.Constant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = Note.class, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = Constant.DATABASE_NAME;
    private static NoteDatabase Instance;

    public abstract NoteDao noteDao();

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized NoteDatabase getInstance(Context context) {
        if (Instance == null) {
            Instance = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, DATABASE_NAME).build();
        }
        return Instance;
    }
}
