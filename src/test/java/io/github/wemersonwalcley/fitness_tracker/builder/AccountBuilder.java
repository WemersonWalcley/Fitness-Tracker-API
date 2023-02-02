package io.github.wemersonwalcley.fitness_tracker.builder;

import io.github.wemersonwalcley.fitness_tracker.model.AccountModel;
import io.github.wemersonwalcley.fitness_tracker.enumeration.AccessLevelEnum;

import java.time.LocalDate;

public class AccountBuilder {

    public static AccountModel createObject(){
        AccountModel accountModel = new AccountModel();
        accountModel.setId(1L);
        accountModel.setCredentialModel(CredentialBuilder.createObject(AccessLevelEnum.ADMIN));
        accountModel.setBirthday(LocalDate.now());
        accountModel.setEmail("email@email.com");
        accountModel.setName("Robert Owen");

        return accountModel;
    }
}
