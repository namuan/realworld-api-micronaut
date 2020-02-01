package realworld.api.micronaut.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
        @JsonInclude
        private String bio;
        @JsonInclude
        private String image;
    }
}
