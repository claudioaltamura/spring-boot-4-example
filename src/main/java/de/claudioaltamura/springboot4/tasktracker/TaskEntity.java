package de.claudioaltamura.springboot4.tasktracker;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.Instant;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA requires a no-args constructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Table(name = "tasks")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long taskId;

    @NotBlank
    private String title;

    @Column(length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private Instant createdAt;

    @Embedded
    private UserId userId;

    public TaskEntity(String title, String description, UserId userId) {
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.status = TaskStatus.TODO;
        this.createdAt = Instant.now();
    }

}