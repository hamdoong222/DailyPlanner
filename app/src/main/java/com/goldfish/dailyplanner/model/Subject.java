package com.goldfish.dailyplanner.model;

import com.goldfish.dailyplanner.dao.Converters;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity
public class Subject {
    @PrimaryKey
    private int id;
    private String subject;
    private String content;
    private boolean checked;
    private Date date;

    public Subject(int id, String subject, String content, boolean checked) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.checked = checked;
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