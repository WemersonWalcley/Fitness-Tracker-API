package io.github.wemersonwalcley.fitness_tracker.DTOS;


import io.github.wemersonwalcley.fitness_tracker.entities.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDTO {
    private Long id;
    private String email;

    public AccountDTO(Account account){
        this.email = account.getEmail();
        this.id = account.getId();
    }
}
