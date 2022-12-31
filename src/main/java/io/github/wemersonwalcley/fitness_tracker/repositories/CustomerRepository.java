package io.github.wemersonwalcley.fitness_tracker.repositories;

import io.github.wemersonwalcley.fitness_tracker.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
