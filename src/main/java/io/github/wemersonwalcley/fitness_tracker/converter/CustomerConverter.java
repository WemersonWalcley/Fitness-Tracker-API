package io.github.wemersonwalcley.fitness_tracker.converter;

import io.github.wemersonwalcley.fitness_tracker.dtos.CustomerDTO;
import io.github.wemersonwalcley.fitness_tracker.model.CustomerModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {

    public CustomerDTO convertEntityToDto(CustomerModel customerModel){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customerModel, CustomerDTO.class);
    }

    public CustomerModel convertDtoToEntity(CustomerDTO customerDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customerDTO, CustomerModel.class);
    }
}

