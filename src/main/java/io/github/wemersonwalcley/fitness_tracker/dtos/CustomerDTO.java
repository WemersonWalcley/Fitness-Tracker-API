package io.github.wemersonwalcley.fitness_tracker.dtos;

import io.github.wemersonwalcley.fitness_tracker.entity.Account;
import lombok.Data;

@Data
public class CustomerDTO {
    private String listaTreino;
    private Account account;
}
