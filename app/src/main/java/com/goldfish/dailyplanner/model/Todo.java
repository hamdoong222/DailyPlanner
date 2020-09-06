package com.goldfish.dailyplanner.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Todo {
    @NonNull
    @PrimaryKey
    private String id;
    private boolean checked;
    private String content;
    private Date time;

    public Todo(@NonNull String id, boolean checked, String content, Date time) {
        this.id = id;
        this.checked = checked;
        this.content = content;
        this.time = time;
    }

    @Ignore
    public Todo(String id, boolean checked, String content, Date time, String string) {
        this.id = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(time) + "-" + id;
        this.checked = checked;
        this.content = content;
        this.time = time;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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
