package io.github.wemersonwalcley.fitness_tracker.builder;

import io.github.wemersonwalcley.fitness_tracker.entity.CredentialEntity;
import io.github.wemersonwalcley.fitness_tracker.enumeration.AccessLevelEnum;

public class CredentialBuilder {

    public static CredentialEntity createObject(){
        CredentialEntity credentialEntity = new CredentialEntity();
        credentialEntity.setId(1L);
        credentialEntity.setPassword("password");
        credentialEntity.setAccessLevelEnum(AccessLevelEnum.ADMIN);
        credentialEntity.setUsername("username");

        return credentialEntity;
    }
}
