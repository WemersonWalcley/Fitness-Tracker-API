package io.github.wemersonwalcley.fitness_tracker.services.credential;

import io.github.wemersonwalcley.fitness_tracker.DTOS.CredentialDTO;
import io.github.wemersonwalcley.fitness_tracker.DTOS.TokenDTO;
import org.springframework.http.ResponseEntity;

public interface CredentialService {
    ResponseEntity<TokenDTO> authenticate (CredentialDTO dto);
}
