package com.pratik.noteappdatabindigandroomdatabase.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.pratik.noteappdatabindigandroomdatabase.models.Note;
import com.pratik.noteappdatabindigandroomdatabase.utils.Constant;

import java.util.List;

@Dao
public interface NoteDao {

//    ""+Todo.COL_YOUR_PRIMARY_KEY" "

//    @Query("SELECT * FROM " + Constant.TABLE_NAME + " ORDER BY " + Constant.TABLE_NOTE + +" DESC")
    @Query("SELECT *  FROM " + Constant.TABLE_NAME + " ORDER BY id DESC")
    LiveData<List<Note>> getNoteList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);

}