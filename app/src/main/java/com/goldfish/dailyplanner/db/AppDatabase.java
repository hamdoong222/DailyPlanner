package com.goldfish.dailyplanner.db;

import com.goldfish.dailyplanner.db.dao.AchievementDao;
import com.goldfish.dailyplanner.db.dao.CommentDao;
import com.goldfish.dailyplanner.db.dao.SubjectDao;
import com.goldfish.dailyplanner.db.dao.TodoDao;
import com.goldfish.dailyplanner.model.Achievement;
import com.goldfish.dailyplanner.model.Comment;
import com.goldfish.dailyplanner.model.Subject;
import com.goldfish.dailyplanner.model.Todo;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Subject.class, Todo.class, Comment.class, Achievement.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract TodoDao todoDao();
    public abstract SubjectDao subjectDao();
    public abstract CommentDao commentDao();
    public abstract AchievementDao achievementDao();
}