package io.github.wemersonwalcley.fitness_tracker.controllers.credential;

import io.github.wemersonwalcley.fitness_tracker.dtos.CredentialDTO;
import io.github.wemersonwalcley.fitness_tracker.dtos.TokenDTO;
import io.github.wemersonwalcley.fitness_tracker.services.credential.CredentialServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Login")
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
