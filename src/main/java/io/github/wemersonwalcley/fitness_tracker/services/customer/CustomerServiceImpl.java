package io.github.wemersonwalcley.fitness_tracker.services.customer;

import io.github.wemersonwalcley.fitness_tracker.entities.Customer;
import io.github.wemersonwalcley.fitness_tracker.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService{

    private final BCryptPasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;

    @Transactional
    public ResponseEntity<Customer> save(Customer customer) {
        String passCrypt = passwordEncoder.encode(customer.getAccount().getCredential().getPassword());
        customer.getAccount().getCredential().setPassword(passCrypt);

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(customerRepository.save(customer));
        } catch (ResponseStatusException e) {
            e.printStackTrace();
            throw new ResponseStatusException(e.getStatus(), e.getMessage());
        }
    }
}
