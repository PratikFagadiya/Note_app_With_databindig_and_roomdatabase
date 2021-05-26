package com.pratik.noteappdatabindigandroomdatabase.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.pratik.noteappdatabindigandroomdatabase.utils.Constant;

@Entity(tableName = Constant.TABLE_NAME)
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = Constant.TABLE_NOTE_TITLE)
    private String title;

    @ColumnInfo(name = Constant.TABLE_NOTE)
    private String note;

    public Note() {
    }

    public Note(int id, String title, String note) {
        this.id = id;
        this.title = title;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
