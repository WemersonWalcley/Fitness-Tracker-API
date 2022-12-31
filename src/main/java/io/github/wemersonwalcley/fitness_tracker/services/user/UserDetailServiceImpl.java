package io.github.wemersonwalcley.fitness_tracker.services.user;

import io.github.wemersonwalcley.fitness_tracker.entities.Credential;
import io.github.wemersonwalcley.fitness_tracker.repositories.LoginRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private LoginRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Credential> optional = repository.findByUsername(username);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new RuntimeException();
    }
}
