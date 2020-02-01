package realworld.api.micronaut.auth;

import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Singleton;

@Singleton
public class AuthService {
    private UserRepository userRepository;
    private TokenService tokenService;


    public AuthService(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public UserResponse registerUser(UserRequest userRequest) {
        UserEntity user = new UserEntity();
        user.setUsername(userRequest.getUser().getUsername());
        user.setEmail(userRequest.getUser().getEmail());
        user.setHashedPassword(hashedPassword(userRequest.getUser().getPassword()));

        UserEntity savedEntity = this.userRepository.save(user);

        UserResponse userResponse = new UserResponse();
        userResponse.getUser().setEmail(savedEntity.getEmail());
        userResponse.getUser().setUsername(savedEntity.getUsername());
        userResponse.getUser().setBio(savedEntity.getBio());
        userResponse.getUser().setImage(savedEntity.getImage());
        userResponse.getUser().setToken(tokenService.generateToken(savedEntity.getId()));

        return userResponse;
    }

    private String hashedPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

}
