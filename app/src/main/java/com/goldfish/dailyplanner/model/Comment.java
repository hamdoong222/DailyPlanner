package com.goldfish.dailyplanner.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Comment {
    @NonNull
    @PrimaryKey
    private String id;
    private String content;
    private Date time;

    public Comment(Date time, String content) {
        this.id = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(time);
        this.content = content;
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}