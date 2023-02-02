package io.github.wemersonwalcley.fitness_tracker.model;

import io.github.wemersonwalcley.fitness_tracker.builder.CustomerBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerModelTest {

    CustomerModel object = CustomerBuilder.createObject();

    @Test
    void testGettersAndSetters() {
        assertThat(object.getId()).isEqualTo(object.getId());
        assertThat(object.getAccountModel()).isEqualTo(object.getAccountModel());
        assertThat(object.getListaTreino()).isEqualTo(object.getListaTreino());
    }

    @Test
    void testToString() {
        assertEquals("CustomerModel(id=1, listaTreino=Treino de musculação, " +
                "accountModel=AccountModel(id=1, name=Robert Owen, email=email@email.com, " +
                "birthday=" + LocalDate.now() + ", credentialModel=CredentialModel(id=1, username=username, " +
                "password=password, accessLevelEnum=ADMIN)))", object.toString());
    }

    @Test
    void testAllArgsConstructor() {
        CustomerModel customerModel = new CustomerModel(1L, "Treino de musculação", object.getAccountModel());
        assertEquals(1L, customerModel.getId());
        assertEquals("Treino de musculação", object.getListaTreino());
        assertEquals(object.getAccountModel(), customerModel.getAccountModel());
    }

    @Test
    void testNoArgsConstructor() {
        CustomerModel customerModel = new CustomerModel();
        Assertions.assertNull(customerModel.getId());
        Assertions.assertNull(customerModel.getListaTreino());
        Assertions.assertNull(customerModel.getAccountModel());
    }
}
