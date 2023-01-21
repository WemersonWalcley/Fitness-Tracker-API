package io.github.wemersonwalcley.fitness_tracker.converter;

import io.github.wemersonwalcley.fitness_tracker.dtos.CustomerDTO;
import io.github.wemersonwalcley.fitness_tracker.entity.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {

    public CustomerDTO convertEntityToDto(CustomerEntity customerEntity){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customerEntity, CustomerDTO.class);
    }

    public CustomerEntity convertDtoToEntity(CustomerDTO customerDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customerDTO, CustomerEntity.class);
    }
}

