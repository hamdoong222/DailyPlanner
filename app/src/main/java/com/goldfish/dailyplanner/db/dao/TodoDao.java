package com.goldfish.dailyplanner.db.dao;

import com.goldfish.dailyplanner.model.Todo;

import java.util.Date;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTodo(Todo todo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTodoList(List<Todo> todoList);

    @Delete
    void deleteTodo(Todo todo);

    @Query("DELETE FROM Todo WHERE id=:id")
    void deleteTodo(int id);

    @Query("SELECT * FROM Todo")
    List<Todo> getTodoList();

    @Query("SELECT * FROM Todo WHERE time BETWEEN :from AND :to")
    List<Todo> getTodoList(Date from, Date to);
}
