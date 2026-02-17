package de.claudioaltamura.springboot4.tasktracker;

import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Setter
@Getter
@Schema(name = "TaskRequest", description = "Request payload to create or update a task")
public class TaskRequest {

    @Schema(description = "Title of the task", example = "Write unit tests")
    private String title;

    @Schema(description = "Detailed description of the task", example = "Write unit tests for TaskService")
    private String description;

}


