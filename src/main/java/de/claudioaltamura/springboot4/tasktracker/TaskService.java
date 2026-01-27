package de.claudioaltamura.springboot4.tasktracker;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task create(String title, String description) {
        return repository.save(new Task(title, description));
    }

    public Task update(Long id, String title, String description) {
        Task task = getById(id);
        task.setTitle(title);
        task.setDescription(description);
        return task;
    }

    public void delete(Long id) {
        Task task = getById(id);
        repository.delete(task);
    }

    public Task updateStatus(Long id, TaskStatus status) {
        Task task = getById(id);
        task.setStatus(status);
        return task;
    }

    public List<Task> findAll() {
        return repository.findAll();
    }

    public List<Task> findByStatus(TaskStatus status) {
        return repository.findByStatus(status);
    }

    private Task getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }
}

