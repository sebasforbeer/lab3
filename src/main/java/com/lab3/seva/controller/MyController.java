package com.lab3.seva.controller;


import com.lab3.seva.dataClasses.Update_task_json;
import com.lab3.seva.model.Priority;
import com.lab3.seva.repository.MyTaskRepository;
import com.lab3.seva.model.MyTask;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/execute")
public class MyController {

    private final MyTaskRepository myTaskRepository;

    public MyController(MyTaskRepository myTaskRepository) {
        this.myTaskRepository = myTaskRepository;
    }



    @GetMapping("/get")
    public ResponseEntity<List<MyTask>> get(){
        return new ResponseEntity<>(myTaskRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<MyTask>> getAllTasks(){
        try {
            List<MyTask> tasks = new ArrayList<>(myTaskRepository.findAll());

            if (tasks.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/task")
    public ResponseEntity<MyTask> getTask(@RequestParam("taskType") String taskType){
        Optional<MyTask> data = Optional.ofNullable(myTaskRepository.findByTaskType(taskType));
        //System.out.println(data);
        return data.map(myTask ->
                new ResponseEntity<>(myTask, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping("/add_task")
    public ResponseEntity<?> addTask(@RequestBody MyTask myTask) {
        try {
            myTaskRepository.save(
                    new MyTask(
                            myTask.getTaskType(),
                            myTask.getTaskDateEnd(),
                            myTask.getPriority()
                    )
            );
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("not unique taskType or problems with TaskDateEnd|Priority",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update_task")
    public ResponseEntity<?> updateTask(@RequestBody @NonNull Update_task_json json) {
        try {
            String oldTaskType = json.getTaskType();
            String newTaskType = json.getNewTaskType();
            Date newTaskDateEnd = Date.valueOf(json.getNewTaskDateEnd());
            Priority newPriority = json.getNewPriority();

            Long oldTaskId = myTaskRepository.findByTaskType(oldTaskType).getId();

            myTaskRepository.updateTask(oldTaskId, newTaskType, newTaskDateEnd, newPriority);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/delete_task")
    public ResponseEntity<?> deleteTask(@RequestParam @NonNull String taskType) {
        try {
            //n+1 problem here
            myTaskRepository.delete(myTaskRepository.findByTaskType(taskType));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<List<MyTask>> filterTask(@RequestParam @NonNull Date date){
        try {
            List<MyTask> allTasks = myTaskRepository.findAll();
            List<MyTask> filteredTasks = allTasks.stream()
                    .filter(task -> {
                        Date taskEndDate = task.getTaskDateEnd();
                        return taskEndDate.after(date) || taskEndDate.equals(date);
                    })
                    .sorted(Comparator.comparing(MyTask::getPriority).reversed())
                    .collect(Collectors.toList());
            return new ResponseEntity<>(filteredTasks, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/clean")
    public ResponseEntity<?> cleanTasks() {
        myTaskRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.GONE);
    }
}
