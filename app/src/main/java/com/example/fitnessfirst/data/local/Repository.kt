package com.example.fitnessfirst.data.local

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.fitnessfirst.data.local.dao.ExEssentialDao
import com.example.fitnessfirst.data.local.dao.ExerciseDao
import com.example.fitnessfirst.data.local.dao.TodoTaskDao
import com.example.fitnessfirst.data.local.entities.Exercise
import com.example.fitnessfirst.data.local.entities.ExerciseEssentials
import com.example.fitnessfirst.data.local.entities.TodoTask
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Repository(application: Application?) {
    private val todoTaskDao: TodoTaskDao
    private val essentialDao: ExEssentialDao
    private val exerciseDao: ExerciseDao

    val allTodoTasks: LiveData<List<TodoTask>>
    val allExEssentials: LiveData<List<ExerciseEssentials>>

    val chestExercises: LiveData<List<Exercise>>
        get() = exerciseDao.getSpecificExercise("Chest")

    val backExercises: LiveData<List<Exercise>>
        get() = exerciseDao.getSpecificExercise("Back")

    val armsExercises: LiveData<List<Exercise>>
        get() = exerciseDao.getSpecificExercise("Arms")

    val coreExercises: LiveData<List<Exercise>>
        get() = exerciseDao.getSpecificExercise("Core")

    val legsExercises: LiveData<List<Exercise>>
        get() = exerciseDao.getSpecificExercise("Legs")

    val getShoulderExercises: LiveData<List<Exercise>>
        get() = exerciseDao.getSpecificExercise("Shoulder")

    @DelicateCoroutinesApi
    fun insertTodoTask(todoTask: TodoTask?) {
        GlobalScope.launch {
            todoTaskDao.insertTodo(todoTask)
        }
    }

    @DelicateCoroutinesApi
    fun updateTodoTask(todoTask: TodoTask?) {
        GlobalScope.launch {
            todoTaskDao.updateTodo(todoTask)
        }
    }

    @DelicateCoroutinesApi
    fun deleteTodoTask(todoTask: TodoTask?) {
        GlobalScope.launch {
            todoTaskDao.deleteTodo(todoTask)
        }
    }

    @DelicateCoroutinesApi
    fun deleteTodoTaskById(position: Int) {
        GlobalScope.launch {
            todoTaskDao.deleteTodoById(position)
        }
    }

    @DelicateCoroutinesApi
    fun insertExEssentials(exEssentials: ExerciseEssentials?) {
        GlobalScope.launch {
            essentialDao.insertEssentials(exEssentials)
        }
    }

    @DelicateCoroutinesApi
    fun updateExEssentials(exEssentials: ExerciseEssentials?) {
        GlobalScope.launch {
            essentialDao.updateEssentials(exEssentials)
        }
    }

    @DelicateCoroutinesApi
    fun deleteExEssentials(exEssentials: ExerciseEssentials?) {
        GlobalScope.launch {
            essentialDao.deleteEssentials(exEssentials)
        }
    }

    @DelicateCoroutinesApi
    fun insertExercise(exercise: Exercise?) {
        GlobalScope.launch {
            exerciseDao.insertExercise(exercise)
        }
    }

    @DelicateCoroutinesApi
    fun updateExercise(exercise: Exercise?) {
        GlobalScope.launch {
            exerciseDao.updateExercise(exercise)
        }
    }

    @DelicateCoroutinesApi
    fun deleteExercise(exercise: Exercise?) {
        GlobalScope.launch {
            exerciseDao.deleteExercise(exercise)
        }
    }

    init {
        val database = TodoDatabase.getInstance(application)
        todoTaskDao = database.todoTaskDao()
        essentialDao = database.exEssentialDao()
        exerciseDao = database.exerciseDao()

        allTodoTasks = todoTaskDao.allTodoTasks
        allExEssentials = essentialDao.allEssentials
    }
}