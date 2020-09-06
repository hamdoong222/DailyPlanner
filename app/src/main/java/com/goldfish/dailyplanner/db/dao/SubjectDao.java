package com.goldfish.dailyplanner.db.dao;

import com.goldfish.dailyplanner.model.Subject;

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSubjectList(List<Subject> subjectList);

    @Delete
    void deleteSubject(Subject subject);

    @Query("DELETE FROM Subject WHERE id=:id")
    void deleteSubject(int id);

    @Query("SELECT * FROM Subject")
    List<Subject> getSubjectList();

    @Query("SELECT * FROM Subject WHERE time BETWEEN :from AND :to")
    List<Subject> getSubjectList(Date from, Date to);

}
