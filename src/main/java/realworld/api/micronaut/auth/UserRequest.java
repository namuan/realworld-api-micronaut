package realworld.api.micronaut.auth;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;

@Introspected
@Getter
public class UserRequest {
    User user;

    @Getter
    public class User {
        String username;
        String email;
        String password;
    }
}
