package io.github.wemersonwalcley.fitness_tracker.service.credential;

import io.github.wemersonwalcley.fitness_tracker.dtos.CredentialDTO;
import io.github.wemersonwalcley.fitness_tracker.dtos.TokenDTO;
import io.github.wemersonwalcley.fitness_tracker.model.CredentialModel;
import io.github.wemersonwalcley.fitness_tracker.repository.CredentialRepository;
import io.github.wemersonwalcley.fitness_tracker.security.JwtEncoder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class CredentialServiceImpl implements CredentialService {

    private final CredentialRepository credentialRepository;
    private final JwtEncoder jwtEncoder;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<TokenDTO> authenticate(CredentialDTO dto) {
        CredentialModel user = credentialRepository.findByUsername(dto.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado."));
        boolean matches;
        matches = passwordEncoder.matches(dto.getPassword(), user.getPassword());

        if (matches) {
            String token = jwtEncoder.generateToken(user);
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(token);
            tokenDTO.setLogin(dto.getUsername());
            return ResponseEntity.ok(tokenDTO);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados incorretos. Verifique os dados da requisição");
        }
    }
}
