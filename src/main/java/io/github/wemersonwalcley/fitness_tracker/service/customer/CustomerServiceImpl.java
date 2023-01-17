package io.github.wemersonwalcley.fitness_tracker.service.customer;

import io.github.wemersonwalcley.fitness_tracker.converter.CustomerConverter;
import io.github.wemersonwalcley.fitness_tracker.dtos.CustomerDTO;
import io.github.wemersonwalcley.fitness_tracker.entity.Customer;
import io.github.wemersonwalcley.fitness_tracker.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService{

    private final BCryptPasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;

    @Autowired
    private CustomerConverter customerConverter;

    @Transactional
    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = customerConverter.convertDtoToEntity(customerDTO);
        String passCrypt = passwordEncoder.encode(customer.getAccount().getCredential().getPassword());
        customer.getAccount().getCredential().setPassword(passCrypt);

        try {
            customerRepository.save(customer);
            return customerConverter.convertEntityToDto(customer);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(e.getStatus(), e.getMessage());
        }
    }
}
