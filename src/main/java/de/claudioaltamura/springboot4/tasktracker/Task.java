package de.claudioaltamura.springboot4.tasktracker;

import java.time.Instant;

public record Task(
        Long id,
        String title,
        String description,
        TaskStatus status,
        Instant createdAt,
        UserId userId) {
}