package com.goldfish.dailyplanner.db.dao;

import com.goldfish.dailyplanner.model.Comment;

import java.util.Date;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CommentDao {
    @Insert
    void insertComment(Comment comment);

    @Delete
    void deleteComment(Comment comment);

    @Query("SELECT * FROM Comment WHERE time BETWEEN :from AND :to LIMIT 1")
    Comment getComment(Date from, Date to);
}
