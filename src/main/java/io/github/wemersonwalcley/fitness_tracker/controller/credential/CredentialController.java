package io.github.wemersonwalcley.fitness_tracker.controller.credential;

import io.github.wemersonwalcley.fitness_tracker.dtos.CredentialDTO;
import io.github.wemersonwalcley.fitness_tracker.dtos.TokenDTO;
import io.github.wemersonwalcley.fitness_tracker.service.credential.CredentialServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CredentialController {

    @Autowired
    private CredentialServiceImpl loginServiceImpl;

    @PostMapping(path = "/authenticate")
    public ResponseEntity<TokenDTO> authenticate(@Validated @RequestBody CredentialDTO dto) {
        return this.loginServiceImpl.authenticate(dto);
    }
}
