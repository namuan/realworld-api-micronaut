package realworld.api.micronaut.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
public class UserResponse {
    private User user = new User();

    @Getter
    @Setter
    public class User {
        private String email;
        private String username;
        private String token;
        private String bio;
        private String image;
    }
}
