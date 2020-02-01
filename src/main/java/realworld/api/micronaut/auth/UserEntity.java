package realworld.api.micronaut.auth;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @NotNull
    String email;
    @NotNull
    String hashedPassword;
    String username;
    String bio;
    String image;
}
