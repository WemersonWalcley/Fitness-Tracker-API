package io.github.wemersonwalcley.fitness_tracker.controller;

import static org.junit.jupiter.api.Assertions.*;

import io.github.wemersonwalcley.fitness_tracker.controller.customer.CustomerController;
import io.github.wemersonwalcley.fitness_tracker.dtos.CustomerDTO;
import io.github.wemersonwalcley.fitness_tracker.service.customer.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @Test
    void testGetCustomerById() {
        Long id = 1L;
        CustomerDTO expectedCustomer = new CustomerDTO();

        when(customerService.getCustomerById(id)).thenReturn(expectedCustomer);

        CustomerDTO actualCustomer = customerController.getCustomerById(id);

        assertEquals(expectedCustomer, actualCustomer);
        verify(customerService).getCustomerById(id);
    }

    @Test
    void testSave() {
        CustomerDTO customerDTO = new CustomerDTO();
        CustomerDTO expectedCustomer = new CustomerDTO();

        when(customerService.save(customerDTO)).thenReturn(expectedCustomer);

        CustomerDTO actualCustomer = customerController.save(customerDTO);

        assertEquals(expectedCustomer, actualCustomer);
        verify(customerService).save(customerDTO);
    }
}
