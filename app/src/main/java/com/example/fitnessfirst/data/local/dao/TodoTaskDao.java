package com.example.fitnessfirst.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fitnessfirst.data.local.entities.TodoTask;

import java.util.List;

@Dao
public interface TodoTaskDao {

    @Insert
    void insertTodo(TodoTask transaction);

    @Update
    void updateTodo(TodoTask transaction);

    @Delete
    void deleteTodo(TodoTask todoTask);

    @Query("DELETE FROM todo_tasks")
    void deleteAllTodos();

    @Query("SELECT * FROM todo_tasks ORDER BY task_id ASC")
    LiveData<List<TodoTask>> getAllTodoTasks();

    @Query("DELETE FROM todo_tasks WHERE task_id =:position")
    void deleteTodoById(int position);
}
