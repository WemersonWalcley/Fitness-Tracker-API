package io.github.wemersonwalcley.fitness_tracker.service.user;

import io.github.wemersonwalcley.fitness_tracker.entity.Account;
import io.github.wemersonwalcley.fitness_tracker.entity.Credential;
import io.github.wemersonwalcley.fitness_tracker.repository.AccountRepository;
import io.github.wemersonwalcley.fitness_tracker.repository.LoginRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private LoginRepository repository;

    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credential credential = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        Account account = accountRepository.findById(credential.getId()).orElseThrow(() -> new UsernameNotFoundException("Account not found with username: " + username));
        return new User(credential.getUsername(), credential.getPassword(), true, true, true, true, account.getAuthorities());
    }
}
