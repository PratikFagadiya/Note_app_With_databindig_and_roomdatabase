package com.pratik.noteappdatabindigandroomdatabase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.pratik.commonnhelper.BaseActivity;
import com.pratik.noteappdatabindigandroomdatabase.R;
import com.pratik.noteappdatabindigandroomdatabase.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        activityMainBinding.btnAddNote.setOnClickListener(v -> startActivity(new Intent(mContext, NoteEditActivity.class)));
    }
}