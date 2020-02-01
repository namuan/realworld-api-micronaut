package realworld.api.micronaut.auth;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

import javax.validation.Valid;

@Controller
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public UserResponse createUser(@Valid UserRequest userRequest) {
        return this.authService.registerUser(userRequest);
    }
}
