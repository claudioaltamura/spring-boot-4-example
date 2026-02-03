package de.claudioaltamura.springboot4.tasktracker;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskRequest {

    private String title;
    private String description;

}

