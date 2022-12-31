package io.github.wemersonwalcley.fitness_tracker.DTOS;


import io.github.wemersonwalcley.fitness_tracker.entities.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDTO {

    private Long id;
    private String email;
    private LocalDate birthday;
    private String name;

    public AccountDTO(Account account){
        this.email = account.getEmail();
        this.birthday = account.getBirthday();
        this.name = account.getName();
    }
}
