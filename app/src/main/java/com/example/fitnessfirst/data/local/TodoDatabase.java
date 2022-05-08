package com.example.fitnessfirst.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.fitnessfirst.data.local.dao.ExEssentialDao;
import com.example.fitnessfirst.data.local.dao.ExerciseDao;
import com.example.fitnessfirst.data.local.dao.TodoTaskDao;
import com.example.fitnessfirst.data.local.entities.Exercise;
import com.example.fitnessfirst.data.local.entities.ExerciseEssentials;
import com.example.fitnessfirst.data.local.entities.TodoTask;

/**
 * The type Todo database.
 */
@Database(entities = {TodoTask.class, ExerciseEssentials.class, Exercise.class}, version = 1)
public abstract class TodoDatabase extends RoomDatabase {

    private static TodoDatabase instance;

    /**
     * Gets instance.
     *
     * @param context the context
     * @return the instance
     */
    public static synchronized TodoDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TodoDatabase.class, "local_app_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    /**
     * Todo task dao todo task dao.
     *
     * @return the todo task dao
     */
    public abstract TodoTaskDao todoTaskDao();

    /**
     * Ex essential dao ex essential dao.
     *
     * @return the ex essential dao
     */
    public abstract ExEssentialDao exEssentialDao();

    /**
     * Exercise dao exercise dao.
     *
     * @return the exercise dao
     */
    public abstract ExerciseDao exerciseDao();
}
