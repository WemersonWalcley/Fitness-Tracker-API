package io.github.wemersonwalcley.fitness_tracker.repositories;

import io.github.wemersonwalcley.fitness_tracker.entities.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Credential, Long> {

    Optional<Credential> findByUsername(String username);

}
