package io.github.wemersonwalcley.fitness_tracker.service.customer;

import io.github.wemersonwalcley.fitness_tracker.mapper.CustomerMapper;
import io.github.wemersonwalcley.fitness_tracker.dtos.CustomerDTO;
import io.github.wemersonwalcley.fitness_tracker.model.CustomerModel;
import io.github.wemersonwalcley.fitness_tracker.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    public CustomerDTO getCustomerById(Long id) {
        CustomerModel customer = customerRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o id: " + id));
        return customerMapper.convertEntityToDto(customer);
    }

    @Transactional
    public CustomerDTO save(CustomerDTO customerDTO) {
        CustomerModel customerModel = customerMapper.convertDtoToEntity(customerDTO);
        String passCrypt = passwordEncoder.encode(customerModel.getAccountModel().getCredentialModel().getPassword());
        customerModel.getAccountModel().getCredentialModel().setPassword(passCrypt);

        try {
            customerRepository.save(customerModel);
            return customerMapper.convertEntityToDto(customerModel);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(e.getStatus(), e.getMessage());
        }
    }
}
