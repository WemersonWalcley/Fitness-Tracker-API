package io.github.wemersonwalcley.fitness_tracker.service.credential;

import io.github.wemersonwalcley.fitness_tracker.dtos.CredentialDTO;
import io.github.wemersonwalcley.fitness_tracker.dtos.TokenDTO;
import io.github.wemersonwalcley.fitness_tracker.entity.Credential;
import io.github.wemersonwalcley.fitness_tracker.repository.LoginRepository;
import io.github.wemersonwalcley.fitness_tracker.security.JwtEncoder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
@Service
@AllArgsConstructor
public class CredentialServiceImpl implements CredentialService {

    private final LoginRepository loginRepository;
    private final JwtEncoder jwtEncoder;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<TokenDTO> authenticate(CredentialDTO dto) {
        Optional<Credential> user = Optional.ofNullable(loginRepository.findByUsername(dto.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado.")));
        boolean matches = false;
        if (user.isPresent()) {
            matches = passwordEncoder.matches(dto.getPassword(), user.get().getPassword());
        }

        if (matches) {
            String token = jwtEncoder.generateToken(user.get());
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(token);
            tokenDTO.setLogin(dto.getUsername());
            return ResponseEntity.ok(tokenDTO);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados incorretos. Verifique os dados da requisisão");
        }
    }
}
