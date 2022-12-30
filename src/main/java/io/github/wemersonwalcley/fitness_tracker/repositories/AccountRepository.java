package io.github.wemersonwalcley.fitness_tracker.repositories;

import io.github.wemersonwalcley.fitness_tracker.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

    Optional<Account> findByEmail(String email);

}
