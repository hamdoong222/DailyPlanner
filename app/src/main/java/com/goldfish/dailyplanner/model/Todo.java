package com.goldfish.dailyplanner.model;

import com.goldfish.dailyplanner.dao.Converters;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity
public class Todo {
    @PrimaryKey
    private int id;
    private boolean checked;
    private String content;
    private Date date;

    public Todo(int id, boolean checked, String content) {
        this.id = id;
        this.checked = checked;
        this.content = content;
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
