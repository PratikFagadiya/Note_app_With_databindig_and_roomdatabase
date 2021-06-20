package com.pratik.noteappdatabindigandroomdatabase.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.pratik.noteappdatabindigandroomdatabase.R;
import com.pratik.noteappdatabindigandroomdatabase.customview.RevealAnimation;
import com.pratik.noteappdatabindigandroomdatabase.databinding.ActivityNoteEditBinding;
import com.pratik.noteappdatabindigandroomdatabase.models.Note;

import java.util.Random;

public class NoteEditActivity extends BaseActivity {

    ActivityNoteEditBinding activityNoteEditBinding;

    Note noteModel;

    boolean fromCreation;

    //    Reveal Animation
    RevealAnimation mRevealAnimation;

    String[] colorArray = {"FFAB91", "FFCC80", "E6EE9B", "80DEEA", "CF93D9", "F48FB1", "80CBC4"};

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

        mRevealAnimation = new RevealAnimation(activityNoteEditBinding.rootlayout, getIntent(), this);

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
            if (fromCreation) {
                noteModel = new Note();
                noteModel.setColorCode(colorArray[new Random().nextInt(colorArray.length)]);
            }
            noteModel.setTitle(activityNoteEditBinding.etTitle.getText().toString());
            noteModel.setNote(activityNoteEditBinding.etNote.getText().toString());

            if (fromCreation) {
                noteRepository.insertNote(noteModel);
                Toast.makeText(NoteEditActivity.this, "Note saved successfully", Toast.LENGTH_SHORT).show();
            } else {
                noteRepository.updateNote(noteModel);
                Toast.makeText(NoteEditActivity.this, "Note updated successfully", Toast.LENGTH_SHORT).show();
            }

            onBackPressed();
//            finish();
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

    @Override
    public void onBackPressed() {
        mRevealAnimation.unRevealActivity();
    }

//    @Override
//    protected void onDestroy() {
//        mRevealAnimation.unRevealActivity();
//        super.onDestroy();
//    }
}