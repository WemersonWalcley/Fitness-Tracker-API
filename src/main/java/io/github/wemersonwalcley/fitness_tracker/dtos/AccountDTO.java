package io.github.wemersonwalcley.fitness_tracker.dtos;


import io.github.wemersonwalcley.fitness_tracker.entity.AccountEntity;
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

    public AccountDTO(AccountEntity accountEntity){
        this.email = accountEntity.getEmail();
        this.birthday = accountEntity.getBirthday();
        this.name = accountEntity.getName();
    }
}
