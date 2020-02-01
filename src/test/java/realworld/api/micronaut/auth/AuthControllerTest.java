package realworld.api.micronaut.auth;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
class AuthControllerTest {

    @Inject
    @Client("/api")
    HttpClient httpClient;

    private EasyRandom nextRandom = new EasyRandom();

    @Test
    @DisplayName("Should register a new user")
    public void testCreateNewUser() {
        // given
        UserRequest userRequest = nextRandom.nextObject(UserRequest.class);
        MutableHttpRequest<UserRequest> postRequest = HttpRequest.POST("/users", userRequest);

        // when
        HttpResponse<UserResponse> exchange = httpClient.toBlocking().exchange(postRequest, Argument.of(UserResponse.class));

        // then
        assertThat(exchange).isNotNull();
        assertThat(exchange.code()).isEqualTo(HttpStatus.CREATED.getCode());

        Optional<UserResponse> body = exchange.getBody();
        UserResponse userResponse = body.orElseThrow();
        assertThat(userResponse.getUser().getEmail()).isEqualTo(userRequest.getUser().getEmail());
        assertThat(userResponse.getUser().getUsername()).isEqualTo(userRequest.getUser().getUsername());
        assertThat(userResponse.getUser().getToken()).isNotBlank();
        assertThat(userResponse.getUser().getBio()).isNull();
        assertThat(userResponse.getUser().getImage()).isNull();
    }
}