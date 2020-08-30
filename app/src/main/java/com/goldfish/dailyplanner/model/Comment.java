package com.goldfish.dailyplanner.model;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Comment {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String content;
    private Date time;

    @Ignore
    public Comment(String content, Date time) {
        this.content = content;
        this.time = time;
    }

    public Comment(int id, String content) {
        this.id = id;
        this.content = content;
        this.time = new Date();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}