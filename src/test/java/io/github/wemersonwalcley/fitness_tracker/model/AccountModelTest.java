package io.github.wemersonwalcley.fitness_tracker.model;

import io.github.wemersonwalcley.fitness_tracker.builder.AccountBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountModelTest {

    AccountModel accountBuilder = AccountBuilder.createObject();
    AccountModel accountModel = new AccountModel();

    @Test
    void testGettersAndSetters() {
        assertThat(accountBuilder.getId()).isEqualTo(accountBuilder.getId());
        assertThat(accountBuilder.getBirthday()).isEqualTo(accountBuilder.getBirthday());
        assertThat(accountBuilder.getEmail()).isEqualTo(accountBuilder.getEmail());
        assertThat(accountBuilder.getName()).isEqualTo(accountBuilder.getName());
        assertThat(accountBuilder.getCredentialModel()).isEqualTo(accountBuilder.getCredentialModel());
    }

    @Test
    void testToString() {
        assertEquals("AccountModel(id=1, name=Robert Owen, email=email@email.com, " +
                "birthday=" + LocalDate.now() + ", credentialModel=CredentialModel(id=1, username=username, " +
                "password=password, accessLevelEnum=ADMIN))", accountBuilder.toString());
    }

    @Test
    void testAllArgsConstructor() {
        accountModel = new AccountModel(1L, "Robert Owen", "email@email.com", LocalDate.now(), accountBuilder.getCredentialModel());
        assertEquals(1L, accountBuilder.getId());
        assertEquals("Robert Owen", accountBuilder.getName());
        assertEquals("email@email.com", accountBuilder.getEmail());
        assertEquals(LocalDate.now(), accountBuilder.getBirthday());
        assertEquals(accountBuilder.getCredentialModel(), accountModel.getCredentialModel());
    }

    @Test
    void testNoArgsConstructor() {
        Assertions.assertNull(accountModel.getId());
        Assertions.assertNull(accountModel.getName());
        Assertions.assertNull(accountModel.getEmail());
        Assertions.assertNull(accountModel.getBirthday());
        Assertions.assertNull(accountModel.getCredentialModel());
    }



}
