package io.github.wemersonwalcley.fitness_tracker.controller;

import io.github.wemersonwalcley.fitness_tracker.controller.credential.CredentialController;
import io.github.wemersonwalcley.fitness_tracker.dtos.CredentialDTO;
import io.github.wemersonwalcley.fitness_tracker.dtos.TokenDTO;
import io.github.wemersonwalcley.fitness_tracker.service.credential.CredentialServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CredentialControllerTest {

    @Mock
    private CredentialServiceImpl loginServiceImpl;

    @InjectMocks
    private CredentialController controller;

    @Test
    public void testAuthenticate() {
        CredentialDTO dto = new CredentialDTO();
        dto.setUsername("user");
        dto.setPassword("password");
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setLogin("user");
        tokenDTO.setToken("token");
        ResponseEntity<TokenDTO> expectedResponse = ResponseEntity.ok(tokenDTO);
        when(loginServiceImpl.authenticate(any(CredentialDTO.class))).thenReturn(expectedResponse);

        ResponseEntity<TokenDTO> response = controller.authenticate(dto);

        assertEquals(expectedResponse, response);
    }

}
