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

public class RepositoryJava {

    private final TodoTaskDao todoTaskDao;
    private final ExEssentialDao exEssentialDao;
    private final ExerciseDao exerciseDao;

    private final LiveData<List<TodoTask>> allTodoTasks;
    private final LiveData<List<ExerciseEssentials>> allExerciseEssentials;

    public RepositoryJava(Application application) {
        TodoDatabase database = TodoDatabase.getInstance(application);
        todoTaskDao = database.todoTaskDao();
        exEssentialDao = database.exEssentialDao();
        exerciseDao = database.exerciseDao();

        allTodoTasks = todoTaskDao.getAllTodoTasks();
        allExerciseEssentials = exEssentialDao.getAllEssentials();
    }


    public void insertTodoTask(TodoTask todoTask) {
        new TodoAsyncTask(todoTaskDao, Utils.INSERT).execute(todoTask);
    }

    public void updateTodoTask(TodoTask todoTask) {
        new TodoAsyncTask(todoTaskDao, Utils.UPDATE).execute(todoTask);
    }

    public void deleteTodoTask(TodoTask todoTask) {
        new TodoAsyncTask(todoTaskDao, Utils.DELETE).execute(todoTask);
    }

    public LiveData<List<TodoTask>> getAllTodoTasks() {
        return allTodoTasks;
    }


    public void insertExEssentials(ExerciseEssentials exEssentials) {
        new ExEssentialAsyncTask(exEssentialDao, Utils.INSERT).execute(exEssentials);
    }

    public void updateExEssentials(ExerciseEssentials exEssentials) {
        new ExEssentialAsyncTask(exEssentialDao, Utils.UPDATE).execute(exEssentials);
    }

    public void deleteExEssentials(ExerciseEssentials exEssentials) {
        new ExEssentialAsyncTask(exEssentialDao, Utils.DELETE).execute(exEssentials);
    }

    public LiveData<List<ExerciseEssentials>> getAllExerciseEssentials() {
        return allExerciseEssentials;
    }

    public void insertExercise(Exercise exercise) {
        new ExerciseAsyncTask(exerciseDao, Utils.INSERT).execute(exercise);
    }

    public LiveData<List<Exercise>> chestExercises() {
        return exerciseDao.getSpecificExercise("Chest");
    }

    public LiveData<List<Exercise>> backExercises() {
        return exerciseDao.getSpecificExercise("Back");
    }

    public LiveData<List<Exercise>> armsExercises() {
        return exerciseDao.getSpecificExercise("Arms");
    }

    public LiveData<List<Exercise>> coreExercises() {
        return exerciseDao.getSpecificExercise("Core");
    }

    public LiveData<List<Exercise>> legsExercises() {
        return exerciseDao.getSpecificExercise("Legs");
    }

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
