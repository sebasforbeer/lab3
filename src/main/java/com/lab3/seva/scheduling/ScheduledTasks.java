package com.lab3.seva.scheduling;

import com.lab3.seva.model.MyTask;
import com.lab3.seva.repository.MyTaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public class ScheduledTasks {
    private final MyTaskRepository myTaskRepository;
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    public ScheduledTasks(MyTaskRepository myTaskRepository) {
        this.myTaskRepository = myTaskRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        List<MyTask> myTasks = myTaskRepository.findAll();
        for (MyTask task : myTasks) {
            log.info("task id: "+ task.getId());
            log.info("task type: "+task.getTaskType());
            log.info("task date end: "+task.getTaskDateEnd());
            log.info("task priority: "+task.getPriority().name());
        }
    }
}
