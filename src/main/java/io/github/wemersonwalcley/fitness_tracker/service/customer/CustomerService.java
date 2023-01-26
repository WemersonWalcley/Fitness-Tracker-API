package io.github.wemersonwalcley.fitness_tracker.service.customer;

import io.github.wemersonwalcley.fitness_tracker.dtos.CustomerDTO;

public interface CustomerService {
    CustomerDTO getCustomerById(Long id);
    CustomerDTO save (CustomerDTO customerDTO);
}
