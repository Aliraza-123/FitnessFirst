package com.example.fitnessfirst.data.local;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.fitnessfirst.data.local.dao.ExEssentialDao;
import com.example.fitnessfirst.data.local.dao.ExerciseDao;
import com.example.fitnessfirst.data.local.dao.TodoTaskDao;
import com.example.fitnessfirst.data.local.entities.Exercise;
import com.example.fitnessfirst.data.local.entities.ExerciseEssentials;
import com.example.fitnessfirst.data.local.entities.TodoTask;
import com.example.fitnessfirst.utils.Utils;

import java.util.List;

/**
 * The type Repository.
 */
public class Repository {

    private final TodoTaskDao todoTaskDao;
    private final ExEssentialDao exEssentialDao;
    private final ExerciseDao exerciseDao;

    private final LiveData<List<TodoTask>> allTodoTasks;
    private final LiveData<List<ExerciseEssentials>> allExerciseEssentials;

    /**
     * Instantiates a new Repository.
     *
     * @param application the application
     */
    public Repository(Application application) {
        TodoDatabase database = TodoDatabase.getInstance(application);
        todoTaskDao = database.todoTaskDao();
        exEssentialDao = database.exEssentialDao();
        exerciseDao = database.exerciseDao();

        allTodoTasks = todoTaskDao.getAllTodoTasks();
        allExerciseEssentials = exEssentialDao.getAllEssentials();
    }

    /**
     * Insert todo task.
     *
     * @param todoTask the todo task
     */
    public void insertTodoTask(TodoTask todoTask) {
        new TodoAsyncTask(todoTaskDao, Utils.INSERT).execute(todoTask);
    }

    /**
     * Update todo task.
     *
     * @param todoTask the todo task
     */
    public void updateTodoTask(TodoTask todoTask) {
        new TodoAsyncTask(todoTaskDao, Utils.UPDATE).execute(todoTask);
    }

    /**
     * Delete todo task.
     *
     * @param todoTask the todo task
     */
    public void deleteTodoTask(TodoTask todoTask) {
        new TodoAsyncTask(todoTaskDao, Utils.DELETE).execute(todoTask);
    }

    /**
     * Delete all todos.
     */
    public void deleteAllTodos() {
        new TodoAsyncTask(todoTaskDao, Utils.DELETE_ALL).execute();
    }


    /**
     * Gets all todo tasks.
     *
     * @return the all todo tasks
     */
    public LiveData<List<TodoTask>> getAllTodoTasks() {
        return allTodoTasks;
    }


    /**
     * Insert ex essentials.
     *
     * @param exEssentials the ex essentials
     */
    public void insertExEssentials(ExerciseEssentials exEssentials) {
        new ExEssentialAsyncTask(exEssentialDao, Utils.INSERT).execute(exEssentials);
    }

    /**
     * Update ex essentials.
     *
     * @param exEssentials the ex essentials
     */
    public void updateExEssentials(ExerciseEssentials exEssentials) {
        new ExEssentialAsyncTask(exEssentialDao, Utils.UPDATE).execute(exEssentials);
    }

    /**
     * Delete ex essentials.
     *
     * @param exEssentials the ex essentials
     */
    public void deleteExEssentials(ExerciseEssentials exEssentials) {
        new ExEssentialAsyncTask(exEssentialDao, Utils.DELETE).execute(exEssentials);
    }

    /**
     * Gets all exercise essentials.
     *
     * @return the all exercise essentials
     */
    public LiveData<List<ExerciseEssentials>> getAllExerciseEssentials() {
        return allExerciseEssentials;
    }

    /**
     * Insert exercise.
     *
     * @param exercise the exercise
     */
    public void insertExercise(Exercise exercise) {
        new ExerciseAsyncTask(exerciseDao, Utils.INSERT).execute(exercise);
    }

    /**
     * Chest exercises live data.
     *
     * @return the live data
     */
    public LiveData<List<Exercise>> chestExercises() {
        return exerciseDao.getSpecificExercise("Chest");
    }

    /**
     * Back exercises live data.
     *
     * @return the live data
     */
    public LiveData<List<Exercise>> backExercises() {
        return exerciseDao.getSpecificExercise("Back");
    }

    /**
     * Arms exercises live data.
     *
     * @return the live data
     */
    public LiveData<List<Exercise>> armsExercises() {
        return exerciseDao.getSpecificExercise("Arms");
    }

    /**
     * Core exercises live data.
     *
     * @return the live data
     */
    public LiveData<List<Exercise>> coreExercises() {
        return exerciseDao.getSpecificExercise("Core");
    }

    /**
     * Legs exercises live data.
     *
     * @return the live data
     */
    public LiveData<List<Exercise>> legsExercises() {
        return exerciseDao.getSpecificExercise("Legs");
    }

    /**
     * Shoulder exercises live data.
     *
     * @return the live data
     */
    public LiveData<List<Exercise>> shoulderExercises() {
        return exerciseDao.getSpecificExercise("Shoulder");
    }

    private static class TodoAsyncTask extends AsyncTask<TodoTask, Void, Void> {
        private final TodoTaskDao userDao;
        private final Integer code;

        private TodoAsyncTask(TodoTaskDao todoTaskDao, Integer code) {
            this.userDao = todoTaskDao;
            this.code = code;
        }

        @Override
        protected Void doInBackground(TodoTask... todoTasks) {
            switch (code) {
                case Utils.INSERT:
                    userDao.insertTodo(todoTasks[0]);
                    break;
                case Utils.UPDATE:
                    userDao.updateTodo(todoTasks[0]);
                    break;
                case Utils.DELETE:
                    userDao.deleteTodo(todoTasks[0]);
                    break;
                case Utils.DELETE_ALL:
                    userDao.deleteAllTodos();
                    break;

            }
            return null;
        }
    }

    private static class ExEssentialAsyncTask extends AsyncTask<ExerciseEssentials, Void, Void> {
        private final ExEssentialDao transactionsDao;
        private final Integer code;

        private ExEssentialAsyncTask(ExEssentialDao exEssentialDao, Integer code) {
            this.transactionsDao = exEssentialDao;
            this.code = code;
        }

        @Override
        protected Void doInBackground(ExerciseEssentials... exerciseEssentials) {
            switch (code) {
                case Utils.INSERT:
                    transactionsDao.insertEssentials(exerciseEssentials[0]);
                    break;
                case Utils.UPDATE:
                    transactionsDao.updateEssentials(exerciseEssentials[0]);
                    break;
                case Utils.DELETE:
                    transactionsDao.deleteEssentials(exerciseEssentials[0]);
                    break;
                case Utils.DELETE_ALL:
                    transactionsDao.deleteAllEssentials();
            }
            return null;
        }
    }

    private static class ExerciseAsyncTask extends AsyncTask<Exercise, Void, Void> {
        private final ExerciseDao dao;
        private final Integer code;

        private ExerciseAsyncTask(ExerciseDao exerciseDao, Integer code) {
            this.dao = exerciseDao;
            this.code = code;
        }

        @Override
        protected Void doInBackground(Exercise... exercises) {
            switch (code) {
                case Utils.INSERT:
                    dao.insertExercise(exercises[0]);
                    break;
                case Utils.UPDATE:
                    dao.updateExercise(exercises[0]);
                    break;
                case Utils.DELETE:
                    dao.deleteExercise(exercises[0]);
                    break;
            }
            return null;
        }
    }
}
