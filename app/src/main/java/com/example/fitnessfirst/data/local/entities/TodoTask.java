package com.example.fitnessfirst.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_tasks")
public class TodoTask {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    private Long taskId;

    @ColumnInfo(name = "task_title")
    private String taskTitle;

    @ColumnInfo(name = "is_completed")
    private Integer isCompleted;

    public TodoTask(String taskTitle) {
        this.taskTitle = taskTitle;
        this.isCompleted = 0;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Integer getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Integer isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return "TodoTask{" +
                "taskId=" + taskId +
                ", taskTitle='" + taskTitle + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
