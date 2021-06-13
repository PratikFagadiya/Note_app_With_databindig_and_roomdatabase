package com.pratik.noteappdatabindigandroomdatabase.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.pratik.noteappdatabindigandroomdatabase.models.Note;
import com.pratik.noteappdatabindigandroomdatabase.utils.Constant;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM " + Constant.TABLE_NAME)
    LiveData<List<Note>> getNoteList();

    @Insert
    void insertNote(Note note);

    @Query("UPDATE notes SET note=:newNoteString WHERE id = :noteId")
    void updateNote(String newNoteString,int noteId);

    @Delete
    void deleteNote(Note note);

}
