package de.claudioaltamura.springboot4.tasktracker;

import lombok.Getter;

import java.time.Instant;

//TODO record
@Getter
public class TaskError {

    //TODO add openapi annotations

    private final Instant timestamp;
    private final int status;
    private final String error;
    private final String message;

    public TaskError(Instant timestamp, int status, String error, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }

}
