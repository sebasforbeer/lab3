package com.lab3.seva.config;

import com.lab3.seva.controller.MyController;
import com.lab3.seva.repository.MyTaskRepository;
import com.lab3.seva.scheduling.ScheduledTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringFactory {

    private final MyTaskRepository myTaskRepository;

    @Autowired
    public SpringFactory(MyTaskRepository myTaskRepository) {
        this.myTaskRepository = myTaskRepository;
    }

    @Bean
    public MyController myController() {
        return new MyController(myTaskRepository);
    }

    @Bean
    public ScheduledTasks scheduledTasks() {
        return new ScheduledTasks(myTaskRepository);
    }
}
