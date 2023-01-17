package io.github.wemersonwalcley.fitness_tracker.repository;

import io.github.wemersonwalcley.fitness_tracker.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
