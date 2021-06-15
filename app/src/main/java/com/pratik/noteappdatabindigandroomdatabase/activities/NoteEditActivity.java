package com.pratik.noteappdatabindigandroomdatabase.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.pratik.noteappdatabindigandroomdatabase.R;
import com.pratik.noteappdatabindigandroomdatabase.databinding.ActivityNoteEditBinding;
import com.pratik.noteappdatabindigandroomdatabase.models.Note;

public class NoteEditActivity extends BaseActivity {

    ActivityNoteEditBinding activityNoteEditBinding;

    Note noteModel;

    boolean fromCreation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityNoteEditBinding = DataBindingUtil.setContentView(this, R.layout.activity_note_edit);

        Bundle extraBundle = getIntent().getExtras();
        if (extraBundle != null) {
            fromCreation = extraBundle.getBoolean("fromCreation");

            if (!fromCreation) {
                noteModel = (Note) extraBundle.getSerializable("myNoteClass");
            }
        }

        if (fromCreation) {
            activityNoteEditBinding.txtSave.setVisibility(View.VISIBLE);
            activityNoteEditBinding.imgEdit.setVisibility(View.GONE);
        } else {
            activityNoteEditBinding.txtSave.setVisibility(View.GONE);
            activityNoteEditBinding.imgEdit.setVisibility(View.VISIBLE);
        }


        if (noteModel != null) {
            activityNoteEditBinding.etTitle.setText(noteModel.getTitle());
            activityNoteEditBinding.etNote.setText(noteModel.getNote());
        }

        activityNoteEditBinding.imgEdit.setOnClickListener(v -> {
            activityNoteEditBinding.imgEdit.setVisibility(View.GONE);
            activityNoteEditBinding.txtSave.setVisibility(View.VISIBLE);
        });

        activityNoteEditBinding.imgBack.setOnClickListener(v -> onBackPressed());

        activityNoteEditBinding.txtSave.setOnClickListener(v -> {
            noteModel = new Note();
            noteModel.setTitle(activityNoteEditBinding.etTitle.getText().toString());
            noteModel.setNote(activityNoteEditBinding.etNote.getText().toString());

            if (fromCreation) {
                noteRepository.insertNote(noteModel);
                Toast.makeText(NoteEditActivity.this, "Note saved successfully", Toast.LENGTH_SHORT).show();
            } else {
                // FIXME: 10-06-2021 Update not working
                noteRepository.updateNote(noteModel);
                Toast.makeText(NoteEditActivity.this, "Note updated successfully", Toast.LENGTH_SHORT).show();
            }

            finish();
        });

        activityNoteEditBinding.etTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!fromCreation) {
                    if (activityNoteEditBinding.imgEdit.getVisibility() == View.VISIBLE) {
                        activityNoteEditBinding.imgEdit.setVisibility(View.GONE);
                        activityNoteEditBinding.txtSave.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        activityNoteEditBinding.etNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!fromCreation) {
                    if (activityNoteEditBinding.imgEdit.getVisibility() == View.VISIBLE) {
                        activityNoteEditBinding.imgEdit.setVisibility(View.GONE);
                        activityNoteEditBinding.txtSave.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}