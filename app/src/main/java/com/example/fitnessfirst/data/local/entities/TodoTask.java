package com.example.fitnessfirst.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * The type Todo task.
 */
@Entity(tableName = "todo_tasks")
public class TodoTask {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    private Long taskId;

    @ColumnInfo(name = "task_title")
    private String taskTitle;

    @ColumnInfo(name = "is_completed")
    private Integer isCompleted;

    /**
     * Instantiates a new Todo task.
     *
     * @param taskTitle the task title
     */
    public TodoTask(String taskTitle) {
        this.taskTitle = taskTitle;
        this.isCompleted = 0;
    }

    /**
     * Gets task id.
     *
     * @return the task id
     */
    public Long getTaskId() {
        return taskId;
    }

    /**
     * Sets task id.
     *
     * @param taskId the task id
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    /**
     * Gets task title.
     *
     * @return the task title
     */
    public String getTaskTitle() {
        return taskTitle;
    }

    /**
     * Sets task title.
     *
     * @param taskTitle the task title
     */
    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    /**
     * Gets is completed.
     *
     * @return the is completed
     */
    public Integer getIsCompleted() {
        return isCompleted;
    }

    /**
     * Sets is completed.
     *
     * @param isCompleted the is completed
     */
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
