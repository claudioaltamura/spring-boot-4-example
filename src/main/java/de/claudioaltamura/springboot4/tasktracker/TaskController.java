package de.claudioaltamura.springboot4.tasktracker;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody TaskRequest request) {
        return service.create(request.getTitle(), request.getDescription());
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id,
                       @RequestBody TaskRequest request) {
        return service.update(id, request.getTitle(), request.getDescription());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}/status")
    public Task updateStatus(@PathVariable Long id,
                             @RequestBody TaskStatusRequest request) {
        return service.updateStatus(id, request.getStatus());
    }

    @GetMapping
    public List<Task> findAll() {
        return service.findAll();
    }

    @GetMapping("/done")
    public List<Task> findDone() {
        return service.findByStatus(TaskStatus.DONE);
    }

    @GetMapping("/in-progress")
    public List<Task> findInProgress() {
        return service.findByStatus(TaskStatus.IN_PROGRESS);
    }

    @GetMapping("/not-done")
    public List<Task> findNotDone() {
        return service.findByStatus(TaskStatus.TODO);
    }
}

