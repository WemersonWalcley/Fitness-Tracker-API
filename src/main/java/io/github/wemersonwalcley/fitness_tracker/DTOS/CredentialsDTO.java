package io.github.wemersonwalcley.fitness_tracker.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class CredentialsDTO {

    private String email;
    private String password;

    public UsernamePasswordAuthenticationToken toUsernamePasswordAuthenticationToken(){
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
