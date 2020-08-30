package com.goldfish.dailyplanner.model;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Subject {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String subject;
    private String content;
    private boolean checked;
    private Date time;

    public Subject(int id, String subject, String content, boolean checked) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.checked = checked;
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