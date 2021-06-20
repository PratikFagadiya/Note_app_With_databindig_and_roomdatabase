package com.pratik.noteappdatabindigandroomdatabase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pratik.noteappdatabindigandroomdatabase.R;
import com.pratik.noteappdatabindigandroomdatabase.adapters.NoteListAdapter;
import com.pratik.noteappdatabindigandroomdatabase.customview.RevealAnimation;
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

        noteListAdapter = new NoteListAdapter(mContext, this);
        activityMainBinding.rvNoteList.setLayoutManager(new LinearLayoutManager(mContext));
        activityMainBinding.rvNoteList.setAdapter(noteListAdapter);

        noteRepository.getAllNotes().observe(this, notes -> {
            noteArrayList.clear();
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

                Toast.makeText(mContext, "Note Deleted", Toast.LENGTH_SHORT).show();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(activityMainBinding.rvNoteList);

        activityMainBinding.btnAddNote.setOnClickListener(v -> {

            //calculates the center of the View v you are passing
            int revealX = (int) (v.getX() + v.getWidth() / 2);
            int revealY = (int) (v.getY() + v.getHeight() / 2);

            Intent intent = new Intent(mContext, NoteEditActivity.class);
            intent.putExtra(RevealAnimation.EXTRA_CIRCULAR_REVEAL_X, revealX);
            intent.putExtra(RevealAnimation.EXTRA_CIRCULAR_REVEAL_Y, revealY);
            intent.putExtra("fromCreation", true);

            //just start the activity as an shared transition, but set the options bundle to null
            ActivityCompat.startActivity(this, intent, null);

            //to prevent strange behaviours override the pending transitions
            overridePendingTransition(0, 0);

        });

        activityMainBinding.imgSearch.setOnClickListener(v -> {
            activityMainBinding.txtNotes.setVisibility(View.GONE);
            activityMainBinding.imgSearch.setVisibility(View.GONE);
            activityMainBinding.etSearchQuery.setVisibility(View.VISIBLE);
        });

        activityMainBinding.etSearchQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchFilter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    void searchFilter(String text) {
        ArrayList<Note> tempArrayList = new ArrayList<>();
        for (Note note : noteArrayList) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches

            if (note.getTitle().contains(text) || note.getNote().contains(text)) {
                tempArrayList.add(note);
            }
        }
        //update recyclerview
        noteListAdapter.submitNoteList(tempArrayList);
    }

    @Override
    public void onMainNoteClick(View itemView, Note note) {
        //calculates the center of the View v you are passing
        int revealX = (int) (itemView.getX() + itemView.getWidth() / 2);
        int revealY = (int) (itemView.getY() + itemView.getHeight() / 2);

        Intent intent = new Intent(mContext, NoteEditActivity.class);
        intent.putExtra(RevealAnimation.EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(RevealAnimation.EXTRA_CIRCULAR_REVEAL_Y, revealY);
        intent.putExtra("fromCreation", false);
        intent.putExtra("myNoteClass", note);

        //just start the activity as an shared transition, but set the options bundle to null
        ActivityCompat.startActivity(this, intent, null);

        //to prevent strange behaviours override the pending transitions
        overridePendingTransition(0, 0);
    }

    @Override
    public void onBackPressed() {

        if (activityMainBinding.imgSearch.getVisibility() == View.VISIBLE) {
            super.onBackPressed();
            return;
        }

        if (activityMainBinding.etSearchQuery.getVisibility() == View.VISIBLE) {
            activityMainBinding.etSearchQuery.setVisibility(View.GONE);
        }
        if (activityMainBinding.imgSearch.getVisibility() == View.GONE) {
            activityMainBinding.imgSearch.setVisibility(View.VISIBLE);
        }
        if (activityMainBinding.txtNotes.getVisibility() == View.GONE) {
            activityMainBinding.txtNotes.setVisibility(View.VISIBLE);
        }

    }
}