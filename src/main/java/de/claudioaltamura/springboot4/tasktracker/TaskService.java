package de.claudioaltamura.springboot4.tasktracker;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//TODO improve methods
//TODO introduce TaskId
@Service
@Transactional
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task create(UserId userId, String title, String description) {
        TaskEntity savedTask = repository.save(new TaskEntity(title, description,userId));
        return new Task(savedTask.getTaskId(),
                savedTask.getTitle(),
                savedTask.getDescription(),
                savedTask.getStatus(),
                savedTask.getCreatedAt(),
                userId);
    }

    //TODO return Task
    public TaskEntity update(UserId userId, Long taskId, String title, String description) {
        TaskEntity task = getByUserIdAndTaskId(userId, taskId);
        task.setTitle(title);
        task.setDescription(description);
        repository.save(task);
        return task;
    }

    //not found
    public void delete(UserId userId, Long taskId) {
        TaskEntity task = getByUserIdAndTaskId(userId, taskId);
        repository.delete(task);
    }

    //TODO return Task
    public TaskEntity updateStatus(UserId userId, Long taskId, TaskStatus status) {
        TaskEntity task = getByUserIdAndTaskId(userId, taskId);
        task.setStatus(status);
        return task;
    }

    //TODO return Task
    public List<TaskEntity> getByUserIdAndStatus(UserId userId, TaskStatus status) {
        return repository.findByUserIdAndStatus(userId, status);
    }

    //TODO return Task
    private TaskEntity getByUserIdAndTaskId(UserId userId, Long taskId) {
        return repository.findByUserIdAndTaskId(userId, taskId);
    }
}