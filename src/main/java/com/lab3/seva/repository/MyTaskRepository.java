package com.lab3.seva.repository;

import com.lab3.seva.model.MyTask;
import com.lab3.seva.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

public interface MyTaskRepository extends JpaRepository<MyTask, String> {

    @Transactional
    @Modifying
    @Query("update MyTask t set t.taskType = ?2, t.taskDateEnd = ?3, t.priority = ?4 where t.id = ?1")
    void updateTask(Long id, String newTaskType, Date newTaskDateEnd, Priority newPriority);


    MyTask findByTaskType(String taskType);

}
