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

@Database(entities = {TodoTask.class, ExerciseEssentials.class, Exercise.class}, version = 1)
public abstract class TodoDatabase extends RoomDatabase {

    private static TodoDatabase instance;

    public static synchronized TodoDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TodoDatabase.class, "local_app_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract TodoTaskDao todoTaskDao();

    public abstract ExEssentialDao exEssentialDao();

    public abstract ExerciseDao exerciseDao();
}
