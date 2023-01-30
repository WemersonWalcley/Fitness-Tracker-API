package io.github.wemersonwalcley.fitness_tracker.builder;

import io.github.wemersonwalcley.fitness_tracker.entity.AccountEntity;
import io.github.wemersonwalcley.fitness_tracker.enumeration.AccessLevelEnum;

import java.time.LocalDate;

public class AccountBuilder {

    public static AccountEntity createObject(){
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(1L);
        accountEntity.setCredentialEntity(CredentialBuilder.createObject(AccessLevelEnum.ADMIN));
        accountEntity.setBirthday(LocalDate.now());
        accountEntity.setEmail("email@email.com");
        accountEntity.setName("Robert Owen");

        return accountEntity;
    }
}
