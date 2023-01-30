package io.github.wemersonwalcley.fitness_tracker.builder;

import io.github.wemersonwalcley.fitness_tracker.entity.CredentialEntity;
import io.github.wemersonwalcley.fitness_tracker.enumeration.AccessLevelEnum;

public class CredentialBuilder {

    public static CredentialEntity createObject(AccessLevelEnum accessLevelEnum){
        CredentialEntity credentialEntity = new CredentialEntity();
        credentialEntity.setId(1L);
        credentialEntity.setPassword("password");
        credentialEntity.setAccessLevelEnum(accessLevelEnum);
        credentialEntity.setUsername("username");

        return credentialEntity;
    }
}
