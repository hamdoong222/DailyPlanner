package com.goldfish.dailyplanner.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Subject {
    @NonNull
    @PrimaryKey
    private String id;
    private String subject;
    private String content;
    private boolean checked;
    private Date time;

    public Subject(@NonNull String id, String subject, String content, boolean checked, Date time) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.checked = checked;
        this.time = time;
    }

    @Ignore
    public Subject(String id, String subject, String content, boolean checked, Date time, String string) {
        this.id = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(time) + "-" + id;
        this.subject = subject;
        this.content = content;
        this.checked = checked;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}