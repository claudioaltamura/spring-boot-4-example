package de.claudioaltamura.springboot4.tasktracker;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@Entity
@Table(name = "tasks")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @Column(length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private Instant createdAt;

    protected TaskEntity() {
        // JPA
    }

    public TaskEntity(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = TaskStatus.TODO;
        this.createdAt = Instant.now();
    }

}

