package de.claudioaltamura.springboot4.tasktracker;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA requires a no-args constructor
@Embeddable
public class UserId implements Serializable {

    private Long value;

    public UserId(Long value) {
        if (value == null) {
            throw new IllegalArgumentException("UserId cannot be null");
        }
        this.value = value;
    }

}