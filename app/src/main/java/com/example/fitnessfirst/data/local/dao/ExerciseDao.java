package com.example.fitnessfirst.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fitnessfirst.data.local.entities.Exercise;

import java.util.List;

/**
 * The interface Exercise dao.
 */
@Dao
public interface ExerciseDao {

    /**
     * Insert exercise.
     *
     * @param exercise the exercise
     */
    @Insert
    void insertExercise(Exercise exercise);

    /**
     * Update exercise.
     *
     * @param exercise the exercise
     */
    @Update
    void updateExercise(Exercise exercise);

    /**
     * Delete exercise.
     *
     * @param exercise the exercise
     */
    @Delete
    void deleteExercise(Exercise exercise);

    /**
     * Gets specific exercise.
     *
     * @param exType the ex type
     * @return the specific exercise
     */
    @Query("SELECT * FROM exercises WHERE ex_type =:exType")
    LiveData<List<Exercise>> getSpecificExercise(String exType);
}
