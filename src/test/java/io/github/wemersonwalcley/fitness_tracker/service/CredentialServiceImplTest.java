package io.github.wemersonwalcley.fitness_tracker.service;

import io.github.wemersonwalcley.fitness_tracker.dtos.CredentialDTO;
import io.github.wemersonwalcley.fitness_tracker.dtos.TokenDTO;
import io.github.wemersonwalcley.fitness_tracker.model.CredentialModel;
import io.github.wemersonwalcley.fitness_tracker.repository.CredentialRepository;
import io.github.wemersonwalcley.fitness_tracker.security.JwtEncoder;
import io.github.wemersonwalcley.fitness_tracker.service.credential.CredentialServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CredentialServiceImplTest {

    @Mock
    private CredentialRepository credentialRepository;

    @Mock
    private JwtEncoder jwtEncoder;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CredentialServiceImpl credentialService;

    CredentialDTO dto = new CredentialDTO();

    @Test
    void testAuthenticate_Success() {

        dto.setUsername("testuser");
        dto.setPassword("testpassword");

        CredentialModel entity = new CredentialModel();
        entity.setUsername("testuser");
        entity.setPassword("hashedpassword");

        when(credentialRepository.findByUsername("testuser")).thenReturn(Optional.of(entity));
        when(passwordEncoder.matches("testpassword", "hashedpassword")).thenReturn(true);
        when(jwtEncoder.generateToken(entity)).thenReturn("token");

        ResponseEntity<TokenDTO> response = credentialService.authenticate(dto);
        TokenDTO tokenDTO = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assert tokenDTO != null;
        assertEquals("testuser", tokenDTO.getLogin());
        assertEquals("token", tokenDTO.getToken());
    }

    @Test
    void testAuthenticate_IncorrectPassword() {
        CredentialDTO dto = new CredentialDTO();
        dto.setUsername("username");
        dto.setPassword("password");

        CredentialModel user = new CredentialModel();
        user.setPassword("hashed_password");

        Optional<CredentialModel> optionalUser = Optional.of(user);

        when(credentialRepository.findByUsername(dto.getUsername())).thenReturn(optionalUser);
        when(passwordEncoder.matches(dto.getPassword(), user.getPassword())).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> credentialService.authenticate(dto));
    }
}
