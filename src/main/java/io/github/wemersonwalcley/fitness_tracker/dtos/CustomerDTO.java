package io.github.wemersonwalcley.fitness_tracker.dtos;

import io.github.wemersonwalcley.fitness_tracker.model.AccountModel;
import lombok.Data;

@Data
public class CustomerDTO {
    private String listaTreino;
    private AccountModel accountModel;
}
