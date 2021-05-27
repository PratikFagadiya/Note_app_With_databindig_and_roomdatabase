package com.pratik.noteappdatabindigandroomdatabase.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.pratik.noteappdatabindigandroomdatabase.R;
import com.pratik.noteappdatabindigandroomdatabase.databinding.ActivityNoteEditBinding;
import com.pratik.noteappdatabindigandroomdatabase.models.Note;

public class NoteEditActivity extends BaseActivity {

    ActivityNoteEditBinding activityNoteEditBinding;

    boolean fromCreation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityNoteEditBinding = DataBindingUtil.setContentView(this, R.layout.activity_note_edit);

        Bundle extraBundle = getIntent().getExtras();
        if (extraBundle != null) {
            fromCreation = extraBundle.getBoolean("fromCreation");
        }

        if (fromCreation) {
            activityNoteEditBinding.txtSave.setVisibility(View.VISIBLE);
            activityNoteEditBinding.imgEdit.setVisibility(View.GONE);
        }

        activityNoteEditBinding.imgEdit.setOnClickListener(v -> {
            activityNoteEditBinding.imgEdit.setVisibility(View.GONE);
            activityNoteEditBinding.txtSave.setVisibility(View.VISIBLE);
        });

        activityNoteEditBinding.imgBack.setOnClickListener(v -> onBackPressed());

        activityNoteEditBinding.txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note note = new Note();
                note.setTitle(activityNoteEditBinding.etTitle.getText().toString());
                note.setNote(activityNoteEditBinding.etNote.getText().toString());
                noteRepository.insertNote(note);

                Toast.makeText(NoteEditActivity.this, "Note saved successfully", Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }

}