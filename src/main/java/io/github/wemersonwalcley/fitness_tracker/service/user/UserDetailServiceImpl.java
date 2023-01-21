package io.github.wemersonwalcley.fitness_tracker.service.user;

import io.github.wemersonwalcley.fitness_tracker.entity.CredentialEntity;
import io.github.wemersonwalcley.fitness_tracker.repository.CredentialRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private CredentialRepository credentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CredentialEntity credentialEntity = credentialRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new User(credentialEntity.getUsername(), credentialEntity.getPassword(), true, true, true, true, credentialEntity.getAuthorities());
    }
}
