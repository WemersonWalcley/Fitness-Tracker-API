package io.github.wemersonwalcley.fitness_tracker.controllers.customer;

import io.github.wemersonwalcley.fitness_tracker.entities.Customer;
import io.github.wemersonwalcley.fitness_tracker.services.customer.CustomerServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Customer")
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @ApiOperation(value = "save")
    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {
        return customerServiceImpl.save(customer);
    }
}
