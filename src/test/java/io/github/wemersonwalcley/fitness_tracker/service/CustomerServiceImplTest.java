package io.github.wemersonwalcley.fitness_tracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import io.github.wemersonwalcley.fitness_tracker.converter.CustomerConverter;
import io.github.wemersonwalcley.fitness_tracker.dtos.CustomerDTO;
import io.github.wemersonwalcley.fitness_tracker.entity.AccountEntity;
import io.github.wemersonwalcley.fitness_tracker.entity.CredentialEntity;
import io.github.wemersonwalcley.fitness_tracker.entity.CustomerEntity;
import io.github.wemersonwalcley.fitness_tracker.repository.CustomerRepository;
import io.github.wemersonwalcley.fitness_tracker.service.customer.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerConverter customerConverter;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    private CustomerServiceImpl customerService;
    private CustomerEntity customerEntity;
    private CustomerDTO customerDTO;

    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImpl(passwordEncoder, customerRepository, customerConverter);
        customerEntity = new CustomerEntity();
        customerEntity.setId(1L);
        customerDTO = new CustomerDTO();
    }

    @Test
    void getCustomerById_validId_returnsCustomerDTO() {
        when(customerRepository.findById(1L)).thenReturn(java.util.Optional.of(customerEntity));
        when(customerConverter.convertEntityToDto(customerEntity)).thenReturn(customerDTO);

        CustomerDTO result = customerService.getCustomerById(1L);

        assertEquals(customerDTO, result);
    }

    @Test
    void getCustomerById_invalidId_throwsUsernameNotFoundException() {
        when(customerRepository.findById(2L)).thenReturn(java.util.Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> customerService.getCustomerById(2L));
    }

    @Test
    void whenSave_thenReturnCustomerDTO() {
        CustomerEntity customerEntity = new CustomerEntity();
        CustomerDTO customerDTO = new CustomerDTO();
        AccountEntity accountEntity = new AccountEntity();
        CredentialEntity credentialEntity = new CredentialEntity();
        credentialEntity.setPassword("12345");
        accountEntity.setCredentialEntity(credentialEntity);
        customerEntity.setAccountEntity(accountEntity);

        when(customerConverter.convertDtoToEntity(customerDTO)).thenReturn(customerEntity);
        when(passwordEncoder.encode("12345")).thenReturn("54321");
        when(customerRepository.save(customerEntity)).thenReturn(customerEntity);
        when(customerConverter.convertEntityToDto(customerEntity)).thenReturn(customerDTO);

        CustomerDTO savedCustomerDTO = customerService.save(customerDTO);

        assertEquals(customerDTO, savedCustomerDTO);
    }

    @Test
    void whenSaveThrowsException_thenThrowResponseStatusException() {
        CustomerEntity customerEntity = new CustomerEntity();
        CustomerDTO customerDTO = new CustomerDTO();
        AccountEntity accountEntity = new AccountEntity();
        CredentialEntity credentialEntity = new CredentialEntity();
        credentialEntity.setPassword("12345");
        accountEntity.setCredentialEntity(credentialEntity);
        customerEntity.setAccountEntity(accountEntity);

        when(customerConverter.convertDtoToEntity(customerDTO)).thenReturn(customerEntity);
        when(passwordEncoder.encode("12345")).thenReturn("54321");
        when(customerRepository.save(customerEntity)).thenThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));

        assertThrows(ResponseStatusException.class, () -> customerService.save(customerDTO));
    }
}
