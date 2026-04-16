package de.claudioaltamura.springboot4.tasktracker;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//Methoden überarbeiten
@Service
@Transactional
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task create(String title, String description) {
        TaskEntity savedTask = repository.save(new TaskEntity(title, description));
        return new Task(savedTask.getId(), savedTask.getTitle(), savedTask.getDescription(), savedTask.getStatus(), savedTask.getCreatedAt());
    }

    public TaskEntity update(Long id, String title, String description) {
        TaskEntity task = getById(id);
        task.setTitle(title);
        task.setDescription(description);
        return task;
    }

    //not found
    public void delete(Long id) {
        TaskEntity task = getById(id);
        repository.delete(task);
    }

    public TaskEntity updateStatus(Long id, TaskStatus status) {
        TaskEntity task = getById(id);
        task.setStatus(status);
        return task;
    }

    public List<TaskEntity> findAll() {
        return repository.findAll();
    }

    public List<TaskEntity> findByStatus(TaskStatus status) {
        return repository.findByStatus(status);
    }

    private TaskEntity getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }
}

