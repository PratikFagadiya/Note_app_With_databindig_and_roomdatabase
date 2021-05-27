package com.pratik.noteappdatabindigandroomdatabase.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.pratik.noteappdatabindigandroomdatabase.models.Note;
import com.pratik.noteappdatabindigandroomdatabase.utils.Constant;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM " + Constant.TABLE_NAME)
    LiveData<List<Note>> getNoteList();

    @Insert
    void insertNote(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);

}
