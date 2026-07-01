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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Tasks", description = "Task management endpoints")
public class TaskController {

    private final TaskService service;

    private final CurrentUserProvider currentUserProvider;

    public TaskController(CurrentUserProvider currentUserProvider, TaskService service) {
        this.currentUserProvider = currentUserProvider;
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
    public ResponseEntity<Task> create(@AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody TaskRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(currentUserProvider.getCurrentUser(jwt), request.title(), request.description()));
    }

    //TODO return ResponseEntity<Task>
    @Operation(summary = "Update an existing task", description = "Updates title and description of an existing task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TaskEntity update(@AuthenticationPrincipal Jwt jwt,
                             @NotNull @Min(1) @PathVariable Long id,
                             @Valid @RequestBody TaskRequest request) {
        return service.update(currentUserProvider.getCurrentUser(jwt), id, request.title(), request.description());
    }


    //TODO add openapi annotations

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal Jwt jwt,
                       @PathVariable Long id) {
        service.delete(currentUserProvider.getCurrentUser(jwt), id);
    }

    //TODO return ResponseEntity<Task>
    @PutMapping("/{id}/status")
    public TaskEntity updateStatus(@AuthenticationPrincipal Jwt jwt,
                                   @PathVariable Long id,
                                   @RequestBody TaskStatusRequest request) {
        return service.updateStatus(currentUserProvider.getCurrentUser(jwt), id, request.status());
    }

    //TODO return ResponseEntity<Task>
    @GetMapping("/done")
    public List<TaskEntity> findDone(@AuthenticationPrincipal Jwt jwt) {
        return service.getByUserIdAndStatus(currentUserProvider.getCurrentUser(jwt), TaskStatus.DONE);
    }

    //TODO return ResponseEntity<Task>
    @GetMapping("/in-progress")
    public List<TaskEntity> findInProgress(@AuthenticationPrincipal Jwt jwt) {
        return service.getByUserIdAndStatus(currentUserProvider.getCurrentUser(jwt), TaskStatus.IN_PROGRESS);
    }

    //TODO return ResponseEntity<Task>
    @GetMapping("/not-done")
    public List<TaskEntity> findNotDone(@AuthenticationPrincipal Jwt jwt) {
        //TODO TODO and IN_PROGRESS
        return service.getByUserIdAndStatus(currentUserProvider.getCurrentUser(jwt), TaskStatus.TODO);
    }
}

