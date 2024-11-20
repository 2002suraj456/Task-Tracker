package com.project.taskTracker.controllers;

import com.project.taskTracker.dto.TaskDTO;
import com.project.taskTracker.services.TaskTrackerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//GET /tasks: Retrieve all tasks
//GET /tasks/{id}: Retrieve a specific task by ID
//POST /tasks: Create a new task
//PUT /tasks/{id}: Update an existing task
//DELETE /tasks/{id}: Delete a task
//PATCH /tasks/{id}/complete: Mark a task as complete
//


@RestController
@RequestMapping("/tasks")
public class TaskTrackerController {
    final TaskTrackerService taskTrackerService;

    public TaskTrackerController(TaskTrackerService taskTrackerService) {
        this.taskTrackerService = taskTrackerService;
    }

    @GetMapping
    public List<TaskDTO> getAllTasks() {
        return taskTrackerService.getAllTasks();
    }

    @GetMapping(path = "/{id}")
    public TaskDTO getTaskById(@PathVariable Long id) {
        return taskTrackerService.getTaskById(id);
    }

    @PostMapping()
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO) {
        return taskTrackerService.createTask(taskDTO);
    }

    @PutMapping(path = "/{id}")
    public TaskDTO updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        return taskTrackerService.updateTask(id,taskDTO);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteTask(@PathVariable Long id){
        taskTrackerService.deleteTask(id);
    }

    @PatchMapping(path = "/{id}/complete")
    public Optional<TaskDTO> completeTask(@PathVariable Long id){
        return taskTrackerService.completeTask(id);
    }

}
