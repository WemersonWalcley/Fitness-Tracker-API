package io.github.wemersonwalcley.fitness_tracker.service;

import io.github.wemersonwalcley.fitness_tracker.mapper.CustomerMapper;
import io.github.wemersonwalcley.fitness_tracker.dtos.CustomerDTO;
import io.github.wemersonwalcley.fitness_tracker.model.AccountModel;
import io.github.wemersonwalcley.fitness_tracker.model.CredentialModel;
import io.github.wemersonwalcley.fitness_tracker.model.CustomerModel;
import io.github.wemersonwalcley.fitness_tracker.repository.CustomerRepository;
import io.github.wemersonwalcley.fitness_tracker.service.customer.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapper customerMapper;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    private CustomerServiceImpl customerService;
    private CustomerModel customerModel;
    private CustomerDTO customerDTO;

    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImpl(passwordEncoder, customerRepository, customerMapper);
        customerModel = new CustomerModel();
        customerModel.setId(1L);
        customerDTO = new CustomerDTO();
    }

    @Test
    void getCustomerById_validId_returnsCustomerDTO() {
        when(customerRepository.findById(1L)).thenReturn(java.util.Optional.of(customerModel));
        when(customerMapper.convertEntityToDto(customerModel)).thenReturn(customerDTO);

        CustomerDTO result = customerService.getCustomerById(1L);

        assertEquals(customerDTO, result);
    }

    @Test
    void whenSave_thenReturnCustomerDTO() {
        CustomerModel customerModel = new CustomerModel();
        CustomerDTO customerDTO = new CustomerDTO();
        AccountModel accountModel = new AccountModel();
        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setPassword("12345");
        accountModel.setCredentialModel(credentialModel);
        customerModel.setAccountModel(accountModel);

        when(customerMapper.convertDtoToEntity(customerDTO)).thenReturn(customerModel);
        when(passwordEncoder.encode("12345")).thenReturn("54321");
        when(customerRepository.save(customerModel)).thenReturn(customerModel);
        when(customerMapper.convertEntityToDto(customerModel)).thenReturn(customerDTO);

        CustomerDTO savedCustomerDTO = customerService.save(customerDTO);

        assertEquals(customerDTO, savedCustomerDTO);
    }

    @Test
    void whenSaveThrowsException_thenThrowResponseStatusException() {
        CustomerModel customerModel = new CustomerModel();
        CustomerDTO customerDTO = new CustomerDTO();
        AccountModel accountModel = new AccountModel();
        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setPassword("12345");
        accountModel.setCredentialModel(credentialModel);
        customerModel.setAccountModel(accountModel);

        when(customerMapper.convertDtoToEntity(customerDTO)).thenReturn(customerModel);
        when(passwordEncoder.encode("12345")).thenReturn("54321");
        when(customerRepository.save(customerModel)).thenThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));

        assertThrows(ResponseStatusException.class, () -> customerService.save(customerDTO));
    }
}
