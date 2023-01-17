package io.github.wemersonwalcley.fitness_tracker.converter;

import io.github.wemersonwalcley.fitness_tracker.dtos.CustomerDTO;
import io.github.wemersonwalcley.fitness_tracker.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {

    public CustomerDTO convertEntityToDto(Customer customer){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public Customer convertDtoToEntity(CustomerDTO customerDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customerDTO, Customer.class);
    }
}

