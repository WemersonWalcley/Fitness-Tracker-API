package io.github.wemersonwalcley.fitness_tracker.model;

import io.github.wemersonwalcley.fitness_tracker.builder.CredentialBuilder;
import io.github.wemersonwalcley.fitness_tracker.enumeration.AccessLevelEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CredentialModelTest {

    CredentialModel credentialBuilder = CredentialBuilder.createObject(AccessLevelEnum.ADMIN);

    @Test
    void testGetAuthority() {
        CredentialModel credentialBuilderAdmin = CredentialBuilder.createObject(AccessLevelEnum.ADMIN);
        assertEquals("ROLE_ADMIN", credentialBuilderAdmin.getAuthority());

        CredentialModel credentialBuilderUser = CredentialBuilder.createObject(AccessLevelEnum.USER);
        assertEquals("ROLE_USER", credentialBuilderUser.getAuthority());

        CredentialModel credentialBuilderDefault = CredentialBuilder.createObject(AccessLevelEnum.DEFAULT);
        assertEquals("ROLE_DEFAULT", credentialBuilderDefault.getAuthority());
    }

    @Test
    void testGettersAndSetters() {
        assertThat(credentialBuilder.getAuthorities()).isEqualTo(credentialBuilder.getAuthorities());
        assertThat(credentialBuilder.isAccountNonExpired()).isTrue();
        assertThat(credentialBuilder.isAccountNonLocked()).isTrue();
        assertThat(credentialBuilder.isCredentialsNonExpired()).isTrue();
        assertThat(credentialBuilder.isEnabled()).isTrue();
    }

    @Test
    void testAllArgsConstructor() {
        CredentialModel credentialModel = new CredentialModel(1L, "Robert Owen", "password", AccessLevelEnum.ADMIN);
        assertEquals(1L, credentialModel.getId());
        assertEquals("Robert Owen", credentialModel.getUsername());
        assertEquals("password", credentialModel.getPassword());
    }

    @Test
    void testNoArgsConstructor() {
        CredentialModel credentialModel = new CredentialModel();
        Assertions.assertNull(credentialModel.getId());
        Assertions.assertNull(credentialModel.getUsername());
        Assertions.assertNull(credentialModel.getPassword());
        Assertions.assertNull(credentialModel.getAccessLevelEnum());
    }

    @Test
    void testToString() {
        assertEquals("CredentialModel(id=1, username=username, password=password, accessLevelEnum=ADMIN)", credentialBuilder.toString());
    }

}
