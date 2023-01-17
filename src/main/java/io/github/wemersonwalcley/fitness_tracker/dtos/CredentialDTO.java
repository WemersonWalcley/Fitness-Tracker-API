package io.github.wemersonwalcley.fitness_tracker.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class CredentialDTO {

    private String username;
    private String password;

    public UsernamePasswordAuthenticationToken toUsernamePasswordAuthenticationToken(){
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}
