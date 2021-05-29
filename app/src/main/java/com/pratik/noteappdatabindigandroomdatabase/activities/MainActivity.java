package com.pratik.noteappdatabindigandroomdatabase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.pratik.noteappdatabindigandroomdatabase.R;
import com.pratik.noteappdatabindigandroomdatabase.adapters.NoteListAdapter;
import com.pratik.noteappdatabindigandroomdatabase.databinding.ActivityMainBinding;
import com.pratik.noteappdatabindigandroomdatabase.models.Note;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class MainActivity extends BaseActivity {

    ActivityMainBinding activityMainBinding;

    NoteListAdapter noteListAdapter;

    ArrayList<Note> noteArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        activityMainBinding.btnAddNote.setOnClickListener(v -> startActivity(new Intent(mContext, NoteEditActivity.class).putExtra("fromCreation", true)));

        noteListAdapter = new NoteListAdapter(mContext);
        activityMainBinding.rvNoteList.setLayoutManager(new LinearLayoutManager(mContext));
        activityMainBinding.rvNoteList.setAdapter(noteListAdapter);

        noteRepository.getAllNotes().observe(this, notes -> {
            noteArrayList.clear();
            Collections.reverse(notes);
            noteArrayList.addAll(notes);
            noteListAdapter.submitNoteList(noteArrayList);
        });
    }
}