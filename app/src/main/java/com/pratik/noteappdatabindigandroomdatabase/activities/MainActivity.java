package com.pratik.noteappdatabindigandroomdatabase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.pratik.noteappdatabindigandroomdatabase.R;
import com.pratik.noteappdatabindigandroomdatabase.adapters.NoteListAdapter;
import com.pratik.noteappdatabindigandroomdatabase.databinding.ActivityMainBinding;
import com.pratik.noteappdatabindigandroomdatabase.interfaces.MainNoteClickListener;
import com.pratik.noteappdatabindigandroomdatabase.models.Note;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends BaseActivity implements MainNoteClickListener {

    ActivityMainBinding activityMainBinding;

    NoteListAdapter noteListAdapter;

    ArrayList<Note> noteArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        activityMainBinding.btnAddNote.setOnClickListener(v -> startActivity(new Intent(mContext, NoteEditActivity.class).putExtra("fromCreation", true)));

        noteListAdapter = new NoteListAdapter(mContext, this);
        activityMainBinding.rvNoteList.setLayoutManager(new LinearLayoutManager(mContext));
        activityMainBinding.rvNoteList.setAdapter(noteListAdapter);

        noteRepository.getAllNotes().observe(this, notes -> {
            noteArrayList.clear();
            Collections.reverse(notes);
            noteArrayList.addAll(notes);
            noteListAdapter.submitNoteList(noteArrayList);
        });
    }

    @Override
    public void onMainNoteClick(Note note) {
        startActivity(new Intent(mContext, NoteEditActivity.class).putExtra("fromCreation", false).putExtra("myNoteClass", note));
    }
}