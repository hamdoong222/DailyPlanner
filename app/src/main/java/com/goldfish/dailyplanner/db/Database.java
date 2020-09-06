package com.goldfish.dailyplanner.db;

import android.app.Activity;
import android.provider.CalendarContract;

import com.applandeo.materialcalendarview.EventDay;
import com.goldfish.dailyplanner.R;
import com.goldfish.dailyplanner.model.Achievement;
import com.goldfish.dailyplanner.model.Comment;
import com.goldfish.dailyplanner.model.Subject;
import com.goldfish.dailyplanner.model.Todo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    public void deleteSubject(final int index, Date date) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String id = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(date) + "-" + index;
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

    public void deleteTodo(final int index, Date date) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String id = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(date) + "-" + index;
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

    public void insertAchievement(final Achievement achievement) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.achievementDao().insertAchievement(achievement);
            }
        }).start();
    }

    public void getAchievement(final Date from, final Date to, final ResultCallBack<Achievement> callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Achievement achievement = appDatabase.achievementDao().getAchievement(from, to);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.run(achievement);
                    }
                });
            }
        }).start();
    }

    public void getDateList(final ResultCallBack<List<EventDay>> callBack) {
        new Thread(() -> {
            ArrayList<Date> dates = new ArrayList<>();

            dates.addAll(appDatabase.subjectDao().getDateList());
            dates.addAll(appDatabase.todoDao().getDateList());

            ArrayList<EventDay> calendarList = new ArrayList<>();

            for (Date date : dates) {
                if (date != null) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    calendarList.add(new EventDay(calendar, R.mipmap.ic_launcher));
                }
            }

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    callBack.run(calendarList);
                }
            });
        }).start();
    }

    public interface ResultCallBack<T> {
        void run(T result);
    }

}