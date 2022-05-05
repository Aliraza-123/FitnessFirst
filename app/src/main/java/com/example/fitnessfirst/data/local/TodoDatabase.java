package com.example.fitnessfirst.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.fitnessfirst.data.local.dao.TodoTaskDao;
import com.example.fitnessfirst.data.local.entities.TodoTask;

@Database(entities = {TodoTask.class}, version = 1)
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
}
