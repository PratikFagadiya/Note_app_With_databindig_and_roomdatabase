package com.pratik.noteappdatabindigandroomdatabase.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pratik.noteappdatabindigandroomdatabase.R;
import com.pratik.noteappdatabindigandroomdatabase.adapters.NoteListAdapter;
import com.pratik.noteappdatabindigandroomdatabase.databinding.ActivityMainBinding;
import com.pratik.noteappdatabindigandroomdatabase.interfaces.MainNoteClickListener;
import com.pratik.noteappdatabindigandroomdatabase.models.Note;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends BaseActivity implements MainNoteClickListener {

    ActivityMainBinding activityMainBinding;

    NoteListAdapter noteListAdapter;

    ArrayList<Note> noteArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();

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

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition();
                noteRepository.deleteNote(noteArrayList.get(position));
                noteArrayList.remove(position);
                noteListAdapter.notifyItemRemoved(position);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(activityMainBinding.rvNoteList);

    }


    @Override

    public void onMainNoteClick(Note note) {
        startActivity(new Intent(mContext, NoteEditActivity.class).putExtra("fromCreation", false).putExtra("myNoteClass", note));
    }
}