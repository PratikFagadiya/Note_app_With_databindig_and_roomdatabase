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
import com.pratik.noteappdatabindigandroomdatabase.databinding.FragmentNoteListBinding;

public class NoteListFragment extends Fragment {

    FragmentNoteListBinding fragmentNoteListBinding;

    public NoteListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentNoteListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_list, container, false);
        return fragmentNoteListBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentNoteListBinding.btnAddNote.setOnClickListener(v -> NavHostFragment.findNavController(NoteListFragment.this).navigate(R.id.action_noteListFragment2_to_noteEditFragment2));
    }
}