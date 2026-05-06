package de.claudioaltamura.springboot4.tasktracker;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Tasks", description = "Task management endpoints")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @Operation(summary = "Create a new task", description = "Creates a new task with title and description")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created"),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TaskError.class)))
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> create(@Valid @RequestBody TaskRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request.getTitle(), request.getDescription()));
    }

    @Operation(summary = "Update an existing task", description = "Updates title and description of an existing task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TaskEntity update(@NotNull @Min(1) @PathVariable Long id,
                             @Valid @RequestBody TaskRequest request) {
        return service.update(id, request.getTitle(), request.getDescription());
    }


    //TODO add openapi annotations

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}/status")
    public TaskEntity updateStatus(@PathVariable Long id,
                                   @RequestBody TaskStatusRequest request) {
        return service.updateStatus(id, request.getStatus());
    }

    @GetMapping
    public List<TaskEntity> findAll() {
        return service.findAll();
    }

    @GetMapping("/done")
    public List<TaskEntity> findDone() {
        return service.findByStatus(TaskStatus.DONE);
    }

    @GetMapping("/in-progress")
    public List<TaskEntity> findInProgress() {
        return service.findByStatus(TaskStatus.IN_PROGRESS);
    }

    @GetMapping("/not-done")
    public List<TaskEntity> findNotDone() {
        //TODO TODO and IN_PROGRESS
        return service.findByStatus(TaskStatus.TODO);
    }
}

