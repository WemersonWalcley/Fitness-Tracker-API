package io.github.wemersonwalcley.fitness_tracker.controller.customer;

import io.github.wemersonwalcley.fitness_tracker.dtos.CustomerDTO;
import io.github.wemersonwalcley.fitness_tracker.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable("id") Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public CustomerDTO save(@RequestBody CustomerDTO customerDTO) {
        return customerService.save(customerDTO);
    }
}
