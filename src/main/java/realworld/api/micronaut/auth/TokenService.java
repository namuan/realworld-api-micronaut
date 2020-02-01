package realworld.api.micronaut.auth;

import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.token.generator.TokenGenerator;

import javax.inject.Singleton;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Singleton
public class TokenService {
    private int tokenExpiryDuration = (int) TimeUnit.MINUTES.toMillis(5);

    private TokenGenerator tokenGenerator;

    public TokenService(TokenGenerator tokenGenerator) {
        this.tokenGenerator = tokenGenerator;
    }

    public String generateToken(UUID userId) {
        return tokenGenerator.generateToken(
                new UserDetails(userId.toString(), Collections.emptyList()),
                tokenExpiryDuration
        ).orElseThrow();
    }
}
