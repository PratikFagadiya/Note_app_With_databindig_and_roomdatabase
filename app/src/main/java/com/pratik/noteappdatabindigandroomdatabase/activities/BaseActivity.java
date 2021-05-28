package com.pratik.noteappdatabindigandroomdatabase.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.pratik.noteappdatabindigandroomdatabase.database.NoteRepository;

public class BaseActivity extends com.pratik.commonnhelper.BaseActivity {

    NoteRepository noteRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        noteRepository = NoteRepository.getNoteRepository(getApplication());
    }
}
