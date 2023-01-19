package io.github.wemersonwalcley.fitness_tracker.repository;

import io.github.wemersonwalcley.fitness_tracker.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
