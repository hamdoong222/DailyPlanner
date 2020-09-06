package com.goldfish.dailyplanner.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.goldfish.dailyplanner.model.Achievement;
import com.goldfish.dailyplanner.model.Comment;

import java.util.Date;
import java.util.List;

@Dao
public interface AchievementDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAchievement(Achievement achievement);

    @Delete
    void deleteAchievement(Achievement achievement);

    @Query("SELECT * FROM Achievement WHERE time BETWEEN :from AND :to LIMIT 1")
    Achievement getAchievement(Date from, Date to);

    @Query("SELECT time FROM Achievement")
    List<Date> getDateList();
}
