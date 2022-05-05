package com.example.fitnessfirst.data.local

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.fitnessfirst.data.local.dao.ExEssentialDao
import com.example.fitnessfirst.data.local.dao.TodoTaskDao
import com.example.fitnessfirst.data.local.entities.ExerciseEssentials
import com.example.fitnessfirst.data.local.entities.TodoTask
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Repository(application: Application?) {
    private val todoTaskDao: TodoTaskDao
    private val essentialDao: ExEssentialDao
    val allTodoTasks: LiveData<List<TodoTask>>
    val allExEssentials: LiveData<List<ExerciseEssentials>>

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
            essentialDao.insertEssentialDao(exEssentials)
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

    init {
        val database = TodoDatabase.getInstance(application)
        todoTaskDao = database.todoTaskDao()
        essentialDao = database.exEssentialDao()
        allTodoTasks = todoTaskDao.allTodoTasks
        allExEssentials = essentialDao.allEssentials
    }
}