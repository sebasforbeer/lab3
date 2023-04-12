package com.lab3.seva.dataClasses;

import com.lab3.seva.model.Priority;

public class Update_task_json {
    private String taskType;
    private String newTaskType;
    private String newTaskDateEnd;
    private Priority newPriority;

    public Update_task_json(String taskType, String newTaskType, String newTaskDateEnd, Priority newPriority) {
        this.taskType = taskType;
        this.newTaskType = newTaskType;
        this.newTaskDateEnd = newTaskDateEnd;
        this.newPriority = newPriority;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getNewTaskType() {
        return newTaskType;
    }

    public void setNewTaskType(String newTaskType) {
        this.newTaskType = newTaskType;
    }

    public String getNewTaskDateEnd() {
        return newTaskDateEnd;
    }

    public void setNewTaskDateEnd(String newTaskDateEnd) {
        this.newTaskDateEnd = newTaskDateEnd;
    }

    public Priority getNewPriority() {
        return newPriority;
    }

    public void setNewPriority(Priority newPriority) {
        this.newPriority = newPriority;
    }
}
