package io.github.wemersonwalcley.fitness_tracker.dtos;

import io.github.wemersonwalcley.fitness_tracker.entity.AccountEntity;
import lombok.Data;

@Data
public class CustomerDTO {
    private String listaTreino;
    private AccountEntity accountEntity;
}
