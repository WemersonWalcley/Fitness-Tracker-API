package io.github.wemersonwalcley.fitness_tracker.repository;

import io.github.wemersonwalcley.fitness_tracker.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountModel, Long> {

}
