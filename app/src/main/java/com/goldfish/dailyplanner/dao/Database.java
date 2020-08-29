package com.goldfish.dailyplanner.dao;

import android.app.Activity;
import android.content.Context;

import com.goldfish.dailyplanner.R;
import com.goldfish.dailyplanner.model.Subject;

import java.util.Date;
import java.util.List;

import androidx.room.Room;

public class Database {
    public static final String DATABASE_NAME = "planner_db";

    private volatile static Database uniqueInstance;
    private static AppDatabase appDatabase;
    private static Activity activity;

    private Database() {}

    // Lazy Initialization. DCL
    public static Database getInstance(Activity context) {
        if(uniqueInstance == null) {
            synchronized(Database.class) {
                if(uniqueInstance == null) {
                    uniqueInstance = new Database();
                    activity = context;
                    appDatabase = Room.databaseBuilder(context,
                            AppDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return uniqueInstance;
    }

    public void insertSubject(final Subject subject) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.subjectDao().insertSubject(subject);
            }
        }).start();
    }

    public void deleteSubject(final Subject subject) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.subjectDao().deleteSubject(subject);
            }
        }).start();
    }

    public void getSubjectList(final ResultCallBack<List<Subject>> callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Subject> subjectList = appDatabase.subjectDao().getSubjectList();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.run(subjectList);
                    }
                });
            }
        }).start();
    }

    public void getSubjectList(final Date from, final Date to, final ResultCallBack<List<Subject>> callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Subject> subjectList = appDatabase.subjectDao().getTodoList(from, to);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.run(subjectList);
                    }
                });
            }
        }).start();
    }

    public interface ResultCallBack<T> {
        void run(T result);
    }

}