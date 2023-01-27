package io.github.wemersonwalcley.fitness_tracker.entity;

import io.github.wemersonwalcley.fitness_tracker.builder.CustomerBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerEntityTest {

    CustomerEntity object = CustomerBuilder.createObject();

    @Test
    void testGettersAndSetters() {
        assertThat(object.getId()).isEqualTo(object.getId());
        assertThat(object.getAccountEntity()).isEqualTo(object.getAccountEntity());
        assertThat(object.getListaTreino()).isEqualTo(object.getListaTreino());
    }

    @Test
    void testToString() {
        assertEquals("CustomerEntity(id=1, listaTreino=Treino de musculação, " +
                "accountEntity=AccountEntity(id=1, name=Name Example, email=example@email.com, " +
                "birthday=2023-01-27, credentialEntity=CredentialEntity(id=1, username=username, " +
                "password=password, accessLevelEnum=ADMIN)))", object.toString());
    }

    @Test
    void testAllArgsConstructor() {
        CustomerEntity customerEntity = new CustomerEntity(1L, "Treino de musculação", object.getAccountEntity());
        assertEquals(1L, customerEntity.getId());
        assertEquals("Treino de musculação", object.getListaTreino());
        assertEquals(object.getAccountEntity(), customerEntity.getAccountEntity());
    }

    @Test
    void testNoArgsConstructor() {
        CustomerEntity customerEntity = new CustomerEntity();
        Assertions.assertNull(customerEntity.getId());
        Assertions.assertNull(customerEntity.getListaTreino());
        Assertions.assertNull(customerEntity.getAccountEntity());
    }
}
