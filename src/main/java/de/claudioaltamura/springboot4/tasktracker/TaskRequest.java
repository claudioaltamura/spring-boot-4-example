package de.claudioaltamura.springboot4.tasktracker;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Setter
@Getter
@Schema(name = "TaskRequest", description = "Request payload to create or update a task")
public class TaskRequest {

    @Schema(description = "Title of the task",
            example = "Write unit tests",
            nullable = false,
            minLength = 1,
            maxLength = 64)
    @NotNull
    @NotBlank
    @Size(max = 64)
    private String title;

    @Schema(description = "Detailed description of the task",
            example = "Write unit tests for TaskService",
            nullable = false,
            minLength = 1,
            maxLength = 255)
    @NotNull
    @NotBlank
    @Size(max = 255)
    private String description;

}


