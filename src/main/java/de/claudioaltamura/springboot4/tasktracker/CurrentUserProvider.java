package de.claudioaltamura.springboot4.tasktracker;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserProvider {

    public UserId getCurrentUser(Jwt jwt) {
        Long userId = Long.valueOf(jwt.getClaim("user_id"));
        return new UserId(userId);
    }
}