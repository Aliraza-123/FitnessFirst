package com.example.fitnessfirst.data.local

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.fitnessfirst.data.local.dao.TodoTaskDao
import com.example.fitnessfirst.data.local.entities.TodoTask
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Repository(application: Application?) {
    private val todoTaskDao: TodoTaskDao
    val allTodoTasks: LiveData<List<TodoTask>>

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

    init {
        val database = TodoDatabase.getInstance(application)
        todoTaskDao = database.todoTaskDao()
        allTodoTasks = todoTaskDao.allTodoTasks
    }
}