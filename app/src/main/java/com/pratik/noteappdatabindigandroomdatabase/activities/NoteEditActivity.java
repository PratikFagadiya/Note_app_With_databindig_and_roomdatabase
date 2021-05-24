package com.pratik.noteappdatabindigandroomdatabase.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.pratik.noteappdatabindigandroomdatabase.R;
import com.pratik.noteappdatabindigandroomdatabase.databinding.ActivityNoteEditBinding;

public class NoteEditActivity extends AppCompatActivity {

    ActivityNoteEditBinding activityNoteEditBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityNoteEditBinding = DataBindingUtil.setContentView(this, R.layout.activity_note_edit);
    }
}