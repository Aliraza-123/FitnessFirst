package com.example.fitnessfirst.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fitnessfirst.data.local.entities.ExerciseEssentials;

import java.util.List;

/**
 * The interface Ex essential dao.
 */
@Dao
public interface ExEssentialDao {

    /**
     * Insert essentials.
     *
     * @param exerciseEssentials the exercise essentials
     */
    @Insert
    void insertEssentials(ExerciseEssentials exerciseEssentials);

    /**
     * Update essentials.
     *
     * @param exerciseEssentials the exercise essentials
     */
    @Update
    void updateEssentials(ExerciseEssentials exerciseEssentials);

    /**
     * Delete essentials.
     *
     * @param exerciseEssentials the exercise essentials
     */
    @Delete
    void deleteEssentials(ExerciseEssentials exerciseEssentials);

    /**
     * Gets all essentials.
     *
     * @return the all essentials
     */
    @Query("SELECT * FROM exercise_essentials ORDER BY ex_essential_id ASC")
    LiveData<List<ExerciseEssentials>> getAllEssentials();

    /**
     * Delete all essentials.
     */
    @Query("DELETE FROM exercise_essentials")
    void deleteAllEssentials();

}
