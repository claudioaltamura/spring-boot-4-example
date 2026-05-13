package de.claudioaltamura.springboot4.tasktracker;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findByUserId(UserId userId);

    TaskEntity findByUserIdAndTaskId(UserId userId, Long taskId);

    List<TaskEntity> findByUserIdAndStatus(UserId userId, TaskStatus status);

}

