package com.project.taskTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

//Each task should have these properties:
//
//id (unique identifier)
//title (string)
//description (string)
//due_date (date)
//status (enum: "pending", "in_progress", "completed")
//created_at (timestamp)
//updated_at (timestamp)


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    enum Status {
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }

    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

