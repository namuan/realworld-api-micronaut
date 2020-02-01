package realworld.api.micronaut.auth;

import javax.inject.Singleton;
import java.nio.charset.Charset;
import java.security.SecureRandom;

@Singleton
public class AuthService {
    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse registerUser(UserRequest userRequest) {
        UserEntity user = new UserEntity();
        user.setEmail(userRequest.getUser().getEmail());
        user.setHashedPassword(hashedPassword(userRequest.getUser().getPassword()));
        UserEntity savedEntity = this.userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        userResponse.getUser().setEmail(savedEntity.getEmail());
        return userResponse;
    }

    private String hashedPassword(String plainTextPassword) {
        // @todo: Change this to securely hash the plain text password
        return plainTextPassword;
    }
}
