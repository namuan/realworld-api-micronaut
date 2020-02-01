package realworld.api.micronaut.auth;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import javax.validation.Valid;

@Controller("/api/users")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<UserResponse> createUser(@Valid UserRequest userRequest) {
        return HttpResponse.created(this.authService.registerUser(userRequest));
    }
}
