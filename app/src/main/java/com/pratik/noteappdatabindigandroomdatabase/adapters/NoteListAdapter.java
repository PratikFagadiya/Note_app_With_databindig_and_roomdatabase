package com.pratik.noteappdatabindigandroomdatabase.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pratik.noteappdatabindigandroomdatabase.databinding.MainNoteListItemBinding;
import com.pratik.noteappdatabindigandroomdatabase.interfaces.MainNoteClickListener;
import com.pratik.noteappdatabindigandroomdatabase.models.Note;

import java.util.ArrayList;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder> {
    Context mContext;
    ArrayList<Note> noteArrayList;

    MainNoteClickListener mainNoteClickListener;

    public NoteListAdapter(Context mContext, MainNoteClickListener mainNoteClickListener) {
        this.mContext = mContext;
        this.mainNoteClickListener = mainNoteClickListener;
        noteArrayList = new ArrayList<>();
    }

    @NonNull
    @Override
    public NoteListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MainNoteListItemBinding mainNoteListItemBinding = MainNoteListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(mainNoteListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteListAdapter.ViewHolder holder, int position) {
        holder.mainNoteListItemBinding.setNote(noteArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return noteArrayList.size();
    }

    public void submitNoteList(ArrayList<Note> noteArrayList) {
        this.noteArrayList = noteArrayList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final MainNoteListItemBinding mainNoteListItemBinding;

        public ViewHolder(@NonNull MainNoteListItemBinding mainNoteListItemBinding) {
            super(mainNoteListItemBinding.getRoot());
            this.mainNoteListItemBinding = mainNoteListItemBinding;

            this.mainNoteListItemBinding.getRoot().setOnClickListener(v -> mainNoteClickListener.onMainNoteClick(noteArrayList.get(getAdapterPosition())));
        }
    }
}
