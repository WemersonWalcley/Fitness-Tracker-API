package io.github.wemersonwalcley.fitness_tracker.entity;

import io.github.wemersonwalcley.fitness_tracker.builder.CredentialBuilder;
import io.github.wemersonwalcley.fitness_tracker.enumeration.AccessLevelEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CredentialEntityTest {

    CredentialEntity credentialBuilder = CredentialBuilder.createObject();

    @Test
    void testAllArgsConstructor() {
        CredentialEntity credentialEntity = new CredentialEntity(1L, "Robert Owen", "password", AccessLevelEnum.ADMIN);
        assertEquals(1L, credentialEntity.getId());
        assertEquals("Robert Owen", credentialEntity.getUsername());
        assertEquals("password", credentialEntity.getPassword());
    }

}
