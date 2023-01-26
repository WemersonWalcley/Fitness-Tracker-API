package io.github.wemersonwalcley.fitness_tracker.controller.customer;

import io.github.wemersonwalcley.fitness_tracker.dtos.CustomerDTO;
import io.github.wemersonwalcley.fitness_tracker.service.customer.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerServiceImpl;


    @GetMapping(value = "/{id}")
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        return customerServiceImpl.getCustomerById(id);
    }

    @PostMapping
    public CustomerDTO save(@RequestBody CustomerDTO customerDTO) {
        return customerServiceImpl.save(customerDTO);
    }
}
