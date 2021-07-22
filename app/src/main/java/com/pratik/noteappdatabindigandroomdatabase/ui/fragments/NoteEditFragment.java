package com.pratik.noteappdatabindigandroomdatabase.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pratik.noteappdatabindigandroomdatabase.R;
import com.pratik.noteappdatabindigandroomdatabase.databinding.FragmentNoteEditBinding;

public class NoteEditFragment extends Fragment {

    FragmentNoteEditBinding fragmentNoteEditBinding;

    public NoteEditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fragmentNoteEditBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_edit, container, false);
        return fragmentNoteEditBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentNoteEditBinding.imgBack.setOnClickListener(v -> NavHostFragment.findNavController(NoteEditFragment.this).navigateUp());

    }
}