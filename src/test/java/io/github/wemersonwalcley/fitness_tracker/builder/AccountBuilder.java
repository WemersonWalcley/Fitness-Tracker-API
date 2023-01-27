package io.github.wemersonwalcley.fitness_tracker.builder;

import io.github.wemersonwalcley.fitness_tracker.entity.AccountEntity;
import java.time.LocalDate;

public class AccountBuilder {

    public static AccountEntity createObject(){
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(1L);
        accountEntity.setCredentialEntity(CredentialBuilder.createObject());
        accountEntity.setBirthday(LocalDate.now());
        accountEntity.setEmail("example@email.com");
        accountEntity.setName("Name Example");

        return accountEntity;
    }
}
