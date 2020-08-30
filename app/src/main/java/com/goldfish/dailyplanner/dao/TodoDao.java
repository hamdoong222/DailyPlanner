package com.goldfish.dailyplanner.dao;

import com.goldfish.dailyplanner.model.Subject;
import com.goldfish.dailyplanner.model.Todo;

import java.util.Date;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TodoDao {
    @Insert
    void insertTodo(Todo todo);

    @Delete
    void deleteTodo(Todo todo);

    @Query("SELECT * FROM Todo")
    List<Todo> getTodoList();

    @Query("SELECT * FROM Todo WHERE time BETWEEN :from AND :to")
    List<Todo> getTodoList(Date from, Date to);
}
