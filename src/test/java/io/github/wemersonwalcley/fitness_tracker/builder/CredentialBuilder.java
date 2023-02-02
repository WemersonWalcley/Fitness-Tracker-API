package io.github.wemersonwalcley.fitness_tracker.builder;

import io.github.wemersonwalcley.fitness_tracker.model.CredentialModel;
import io.github.wemersonwalcley.fitness_tracker.enumeration.AccessLevelEnum;

public class CredentialBuilder {

    public static CredentialModel createObject(AccessLevelEnum accessLevelEnum){
        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setId(1L);
        credentialModel.setPassword("password");
        credentialModel.setAccessLevelEnum(accessLevelEnum);
        credentialModel.setUsername("username");

        return credentialModel;
    }
}
