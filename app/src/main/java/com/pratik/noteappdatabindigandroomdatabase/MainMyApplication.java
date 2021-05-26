package com.pratik.noteappdatabindigandroomdatabase;

import android.app.Application;

import com.pratik.noteappdatabindigandroomdatabase.database.NoteDatabase;

public class MainMyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        NoteDatabase.getInstance(this);
    }
}
