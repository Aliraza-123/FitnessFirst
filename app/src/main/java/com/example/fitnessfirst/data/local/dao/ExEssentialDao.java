package com.example.fitnessfirst.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fitnessfirst.data.local.entities.ExerciseEssentials;

import java.util.List;

@Dao
public interface ExEssentialDao {

    @Insert
    void insertEssentialDao(ExerciseEssentials exerciseEssentials);

    @Update
    void updateEssentials(ExerciseEssentials exerciseEssentials);

    @Delete
    void deleteEssentials(ExerciseEssentials exerciseEssentials);

    @Query("SELECT * FROM exercise_essentials ORDER BY ex_essential_id ASC")
    LiveData<List<ExerciseEssentials>> getAllEssentials();

}
