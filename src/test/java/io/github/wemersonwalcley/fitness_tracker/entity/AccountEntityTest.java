package io.github.wemersonwalcley.fitness_tracker.entity;

import io.github.wemersonwalcley.fitness_tracker.builder.AccountBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountEntityTest {

    AccountEntity accountBuilder = AccountBuilder.createObject();
    AccountEntity accountEntity = new AccountEntity();

    @Test
    void testGettersAndSetters() {
        assertThat(accountBuilder.getId()).isEqualTo(accountBuilder.getId());
        assertThat(accountBuilder.getBirthday()).isEqualTo(accountBuilder.getBirthday());
        assertThat(accountBuilder.getEmail()).isEqualTo(accountBuilder.getEmail());
        assertThat(accountBuilder.getName()).isEqualTo(accountBuilder.getName());
        assertThat(accountBuilder.getCredentialEntity()).isEqualTo(accountBuilder.getCredentialEntity());
    }

    @Test
    void testToString() {
        assertEquals("AccountEntity(id=1, name=Robert Owen, email=email@email.com, " +
                "birthday=" + LocalDate.now() + ", credentialEntity=CredentialEntity(id=1, username=username, " +
                "password=password, accessLevelEnum=ADMIN))", accountBuilder.toString());
    }

    @Test
    void testAllArgsConstructor() {
        accountEntity = new AccountEntity(1L, "Robert Owen", "email@email.com", LocalDate.now(), accountBuilder.getCredentialEntity());
        assertEquals(1L, accountBuilder.getId());
        assertEquals("Robert Owen", accountBuilder.getName());
        assertEquals("email@email.com", accountBuilder.getEmail());
        assertEquals(LocalDate.now(), accountBuilder.getBirthday());
        assertEquals(accountBuilder.getCredentialEntity(), accountEntity.getCredentialEntity());
    }

    @Test
    void testNoArgsConstructor() {
        Assertions.assertNull(accountEntity.getId());
        Assertions.assertNull(accountEntity.getName());
        Assertions.assertNull(accountEntity.getEmail());
        Assertions.assertNull(accountEntity.getBirthday());
        Assertions.assertNull(accountEntity.getCredentialEntity());
    }



}
