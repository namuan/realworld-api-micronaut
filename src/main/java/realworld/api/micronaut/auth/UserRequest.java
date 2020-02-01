package realworld.api.micronaut.auth;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Introspected
@Getter
public class UserRequest {
    @NotNull
    User user;

    @Getter
    public class User {
        @NotNull
        String username;
        @NotNull
        String email;
        @NotNull
        String password;
    }
}
