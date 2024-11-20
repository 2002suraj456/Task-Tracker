package com.project.taskTracker.services;

import com.project.taskTracker.dto.TaskDTO;
import com.project.taskTracker.entities.TaskEntity;
import com.project.taskTracker.repositories.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskTrackerService {
    final TaskRepository taskRepository;
    final ModelMapper modelMapper;

    public TaskTrackerService(TaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public TaskDTO createTask(TaskDTO taskDTO) {
        TaskEntity taskEntity = modelMapper.map(taskDTO, TaskEntity.class);
        return modelMapper.map(taskRepository.save(taskEntity), TaskDTO.class);
    }

    public List<TaskDTO> getAllTasks() {
         return taskRepository
                .findAll()
                .stream()
                .map(taskEntity -> modelMapper.map(taskEntity, TaskDTO.class))
                .collect(Collectors.toList());
    }

    public TaskDTO getTaskById(Long id) {
        return modelMapper.map(taskRepository.findById(id).orElse(null), TaskDTO.class);
    }

    @Transactional
    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        return taskRepository
                .findById(id)
                .map(existingTask -> {
                    existingTask.setDescription(taskDTO.getDescription());
                    existingTask.setDueDate(taskDTO.getDueDate());
                    existingTask.setTitle(taskDTO.getTitle());
                    return modelMapper.map(taskRepository.save(existingTask), TaskDTO.class);
                })
                .orElseGet(() -> {
                    TaskEntity newTask = modelMapper.map(taskDTO, TaskEntity.class);
                    return modelMapper.map(taskRepository.save(newTask), TaskDTO.class);
                });
    }

    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    public Optional<TaskDTO> completeTask(Long id) {
        return taskRepository
                .findById(id)
                .map(oldtaskentity ->{
                    oldtaskentity.setStatus(TaskEntity.Status.COMPLETED);
                    return modelMapper.map(taskRepository.save(oldtaskentity), TaskDTO.class);
                });
    }
}
