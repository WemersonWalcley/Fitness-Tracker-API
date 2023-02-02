package io.github.wemersonwalcley.fitness_tracker.repository;

import io.github.wemersonwalcley.fitness_tracker.model.CredentialModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredentialRepository extends JpaRepository<CredentialModel, Long> {

    Optional<CredentialModel> findByUsername(String username);

}
