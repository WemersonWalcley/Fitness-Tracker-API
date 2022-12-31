package io.github.wemersonwalcley.fitness_tracker.services.customer;

import io.github.wemersonwalcley.fitness_tracker.entities.Customer;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    ResponseEntity<Customer> save (Customer customer);
}
