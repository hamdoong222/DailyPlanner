package com.goldfish.dailyplanner.db.dao;

import com.goldfish.dailyplanner.model.Comment;

import java.util.Date;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertComment(Comment comment);

    @Delete
    void deleteComment(Comment comment);

    @Query("SELECT * FROM Comment WHERE time BETWEEN :from AND :to LIMIT 1")
    Comment getComment(Date from, Date to);

    @Query("SELECT time FROM Comment")
    List<Date> getDateList();
}
