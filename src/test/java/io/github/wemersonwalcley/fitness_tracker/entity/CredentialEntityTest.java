package io.github.wemersonwalcley.fitness_tracker.entity;

import io.github.wemersonwalcley.fitness_tracker.builder.CredentialBuilder;
import io.github.wemersonwalcley.fitness_tracker.enumeration.AccessLevelEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CredentialEntityTest {

    CredentialEntity credentialBuilder = CredentialBuilder.createObject(AccessLevelEnum.ADMIN);

    @Test
    void testGetAuthority() {
        CredentialEntity credentialBuilderAdmin = CredentialBuilder.createObject(AccessLevelEnum.ADMIN);
        assertEquals("ROLE_ADMIN", credentialBuilderAdmin.getAuthority());

        CredentialEntity credentialBuilderUser = CredentialBuilder.createObject(AccessLevelEnum.USER);
        assertEquals("ROLE_USER", credentialBuilderUser.getAuthority());

        CredentialEntity credentialBuilderDefault = CredentialBuilder.createObject(AccessLevelEnum.DEFAULT);
        assertEquals("ROLE_DEFAULT", credentialBuilderDefault.getAuthority());
    }

    @Test
    void testGettersAndSetters() {
        assertThat(credentialBuilder.getAuthorities()).isEqualTo(credentialBuilder.getAuthorities());
        assertThat(credentialBuilder.isAccountNonExpired()).isEqualTo(true);
        assertThat(credentialBuilder.isAccountNonLocked()).isEqualTo(true);
        assertThat(credentialBuilder.isCredentialsNonExpired()).isEqualTo(true);
        assertThat(credentialBuilder.isEnabled()).isEqualTo(true);
    }

    @Test
    void testAllArgsConstructor() {
        CredentialEntity credentialEntity = new CredentialEntity(1L, "Robert Owen", "password", AccessLevelEnum.ADMIN);
        assertEquals(1L, credentialEntity.getId());
        assertEquals("Robert Owen", credentialEntity.getUsername());
        assertEquals("password", credentialEntity.getPassword());
    }

    @Test
    void testNoArgsConstructor() {
        CredentialEntity credentialEntity = new CredentialEntity();
        Assertions.assertNull(credentialEntity.getId());
        Assertions.assertNull(credentialEntity.getUsername());
        Assertions.assertNull(credentialEntity.getPassword());
        Assertions.assertNull(credentialEntity.getAccessLevelEnum());
    }

    @Test
    void testToString() {
        assertEquals("CredentialEntity(id=1, username=username, password=password, accessLevelEnum=ADMIN)", credentialBuilder.toString());
    }

}
