package io.github.wemersonwalcley.fitness_tracker.service.customer;

import io.github.wemersonwalcley.fitness_tracker.converter.CustomerConverter;
import io.github.wemersonwalcley.fitness_tracker.dtos.CustomerDTO;
import io.github.wemersonwalcley.fitness_tracker.entity.CustomerEntity;
import io.github.wemersonwalcley.fitness_tracker.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerConverter customerConverter;

    public CustomerDTO getCustomerById(Long id) {
        CustomerEntity customer = customerRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o id: " + id));

        try {
            return customerConverter.convertEntityToDto(customer);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(e.getStatus(), e.getMessage());
        }

    }

    @Transactional
    public CustomerDTO save(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerConverter.convertDtoToEntity(customerDTO);
        String passCrypt = passwordEncoder.encode(customerEntity.getAccountEntity().getCredentialEntity().getPassword());
        customerEntity.getAccountEntity().getCredentialEntity().setPassword(passCrypt);

        try {
            customerRepository.save(customerEntity);
            return customerConverter.convertEntityToDto(customerEntity);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(e.getStatus(), e.getMessage());
        }
    }
}
