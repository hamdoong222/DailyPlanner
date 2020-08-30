package com.goldfish.dailyplanner.dao;

import android.app.Activity;

import com.goldfish.dailyplanner.model.Comment;
import com.goldfish.dailyplanner.model.Subject;
import com.goldfish.dailyplanner.model.Todo;

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

    public void insertSubjectList(final Subject subject) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.subjectDao().insertSubject(subject);
            }
        }).start();
    }

    public void insertSubjectList(final List<Subject> subject) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.subjectDao().insertSubjectList(subject);
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

    public void deleteSubject(final int id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.subjectDao().deleteSubject(id);
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
                final List<Subject> subjectList = appDatabase.subjectDao().getSubjectList(from, to);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.run(subjectList);
                    }
                });
            }
        }).start();
    }

    public void insertTodoList(final Todo todo) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.todoDao().insertTodo(todo);
            }
        }).start();
    }

    public void insertTodoList(final List<Todo> todoList) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.todoDao().insertTodoList(todoList);
            }
        }).start();
    }

    public void deleteTodo(final Todo todo) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.todoDao().deleteTodo(todo);
            }
        }).start();
    }

    public void deleteTodo(final int id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.todoDao().deleteTodo(id);
            }
        }).start();
    }

    public void getTodoList(final ResultCallBack<List<Todo>> callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Todo> todoList = appDatabase.todoDao().getTodoList();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.run(todoList);
                    }
                });
            }
        }).start();
    }

    public void getTodoList(final Date from, final Date to, final ResultCallBack<List<Todo>> callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Todo> todoList = appDatabase.todoDao().getTodoList(from, to);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.run(todoList);
                    }
                });
            }
        }).start();
    }

    public void insertComment(final Comment comment) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.commentDao().insertComment(comment);
            }
        }).start();
    }

    public void deleteComment(final Comment comment) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.commentDao().deleteComment(comment);
            }
        }).start();
    }

    public void getComment(final Date from, final Date to, final ResultCallBack<Comment> callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Comment comment = appDatabase.commentDao().getComment(from, to);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.run(comment);
                    }
                });
            }
        }).start();
    }

    public interface ResultCallBack<T> {
        void run(T result);
    }

}