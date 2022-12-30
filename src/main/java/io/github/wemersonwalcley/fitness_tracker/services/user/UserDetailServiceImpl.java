package io.github.wemersonwalcley.fitness_tracker.services.user;

import io.github.wemersonwalcley.fitness_tracker.entities.Account;
import io.github.wemersonwalcley.fitness_tracker.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private AccountRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> optional = repository.findByEmail(username);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new RuntimeException();
    }
}
