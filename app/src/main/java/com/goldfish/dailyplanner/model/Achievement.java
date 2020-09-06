package com.goldfish.dailyplanner.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity
public class Achievement {
    @NonNull
    @PrimaryKey
    private String id;
    private int progress;
    private Date time;

    public Achievement(Date time, int progress) {
        this.id = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(time);
        this.progress = progress;
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

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}