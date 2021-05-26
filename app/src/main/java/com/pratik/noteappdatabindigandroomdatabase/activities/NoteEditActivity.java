package com.pratik.noteappdatabindigandroomdatabase.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.pratik.noteappdatabindigandroomdatabase.R;
import com.pratik.noteappdatabindigandroomdatabase.databinding.ActivityNoteEditBinding;

public class NoteEditActivity extends AppCompatActivity {

    ActivityNoteEditBinding activityNoteEditBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityNoteEditBinding = DataBindingUtil.setContentView(this, R.layout.activity_note_edit);

        activityNoteEditBinding.imgEdit.setOnClickListener(v -> {
            activityNoteEditBinding.imgEdit.setVisibility(View.GONE);
            activityNoteEditBinding.txtSave.setVisibility(View.VISIBLE);
        });

        activityNoteEditBinding.imgBack.setOnClickListener(v -> onBackPressed());
    }

}