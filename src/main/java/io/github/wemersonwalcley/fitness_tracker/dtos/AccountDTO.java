package io.github.wemersonwalcley.fitness_tracker.dtos;


import io.github.wemersonwalcley.fitness_tracker.model.AccountModel;
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

    public AccountDTO(AccountModel accountModel){
        this.email = accountModel.getEmail();
        this.birthday = accountModel.getBirthday();
        this.name = accountModel.getName();
    }
}
