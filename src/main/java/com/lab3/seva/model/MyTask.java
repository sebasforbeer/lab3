package com.lab3.seva.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;


@Entity
public class MyTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String taskType;

    @NotBlank
    private Date taskDateEnd;

    @NotBlank
    private Priority priority;

    public MyTask() {
    }

    public MyTask(String taskType, Date taskDateEnd, Priority priority) {
        this.taskType = taskType;
        this.taskDateEnd = taskDateEnd;
        this.priority = priority;
    }

    public String getTaskType() {
        return taskType;
    }

    public Date getTaskDateEnd() {
        return taskDateEnd;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public void setTaskDateEnd(Date taskDateEnd) {
        this.taskDateEnd = taskDateEnd;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MyTask{" + "taskType='" + taskType + '\'' +
                ", taskDateEnd=" + taskDateEnd +
                ", priority=" + priority +
                '}';
    }
}
