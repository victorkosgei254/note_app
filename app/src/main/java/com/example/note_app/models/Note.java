package com.example.note_app.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @ColumnInfo(name = "note_title")
    private String note_title;
    @ColumnInfo(name = "note_desc")
    private String note_desc;
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    private Integer note_id;

    @Ignore
    public Note(){}
    public Note(String note_title, String note_desc) {
        this.note_title = note_title;
        this.note_desc = note_desc;
        this.note_id = null;
    }

    public String getNote_title() {
        return note_title;
    }

    public void setNote_title(String note_title) {
        this.note_title = note_title;
    }

    public String getNote_desc() {
        return note_desc;
    }

    public void setNote_desc(String note_desc) {
        this.note_desc = note_desc;
    }

    public Integer getNote_id() {
        return note_id;
    }

    public void setNote_id(Integer note_id) {
        this.note_id = note_id;
    }
}
