package com.example.fitnessfirst.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fitnessfirst.data.local.entities.TodoTask;

import java.util.List;

/**
 * The interface Todo task dao.
 */
@Dao
public interface TodoTaskDao {

    /**
     * Insert todo.
     *
     * @param transaction the transaction
     */
    @Insert
    void insertTodo(TodoTask transaction);

    /**
     * Update todo.
     *
     * @param transaction the transaction
     */
    @Update
    void updateTodo(TodoTask transaction);

    /**
     * Delete todo.
     *
     * @param todoTask the todo task
     */
    @Delete
    void deleteTodo(TodoTask todoTask);

    /**
     * Delete all todos.
     */
    @Query("DELETE FROM todo_tasks")
    void deleteAllTodos();

    /**
     * Gets all todo tasks.
     *
     * @return the all todo tasks
     */
    @Query("SELECT * FROM todo_tasks ORDER BY task_id ASC")
    LiveData<List<TodoTask>> getAllTodoTasks();

    /**
     * Delete todo by id.
     *
     * @param position the position
     */
    @Query("DELETE FROM todo_tasks WHERE task_id =:position")
    void deleteTodoById(int position);
}
