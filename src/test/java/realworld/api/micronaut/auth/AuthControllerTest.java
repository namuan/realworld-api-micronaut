package realworld.api.micronaut.auth;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
class AuthControllerTest {

    @Inject
    @Client("/")
    HttpClient httpClient;

    private EasyRandom nextRandom = new EasyRandom();

    @Test
    @DisplayName("Should register a new user")
    public void testCreateNewUser() {
        // given
        UserRequest userRequest = nextRandom.nextObject(UserRequest.class);
        MutableHttpRequest<UserRequest> postRequest = HttpRequest.POST("/", userRequest);

        // when
        HttpResponse<Object> exchange = httpClient.toBlocking().exchange(postRequest);

        // then
        assertThat(exchange).isNotNull();
    }
}