package com.lab3.seva.dataClasses;


import com.lab3.seva.model.Priority;

public class Add_task_json {
    private Priority priority;
    private String taskDateEnd;
    private String taskType;

    public Add_task_json(Priority priority, String taskDateEnd, String taskType) {
        this.priority = priority;
        this.taskDateEnd = taskDateEnd;
        this.taskType = taskType;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getTaskDateEnd() {
        return taskDateEnd;
    }

    public void setTaskDateEnd(String taskDateEnd) {
        this.taskDateEnd = taskDateEnd;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
}
