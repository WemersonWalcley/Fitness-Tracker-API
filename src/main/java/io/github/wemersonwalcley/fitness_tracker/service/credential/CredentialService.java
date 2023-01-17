package io.github.wemersonwalcley.fitness_tracker.service.credential;

import io.github.wemersonwalcley.fitness_tracker.dtos.CredentialDTO;
import io.github.wemersonwalcley.fitness_tracker.dtos.TokenDTO;
import org.springframework.http.ResponseEntity;

public interface CredentialService {
    ResponseEntity<TokenDTO> authenticate (CredentialDTO dto);
}
