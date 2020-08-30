package com.goldfish.dailyplanner.dao;

import com.goldfish.dailyplanner.model.Subject;
import com.goldfish.dailyplanner.model.Todo;

import java.util.Date;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface SubjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSubject(Subject subject);

    @Delete
    void deleteSubject(Subject subject);

    @Query("SELECT * FROM Subject")
    List<Subject> getSubjectList();

    @Query("SELECT * FROM Subject WHERE time BETWEEN :from AND :to")
    List<Subject> getSubjectList(Date from, Date to);

}
