package io.github.wemersonwalcley.fitness_tracker.enumeration;

import lombok.Getter;

@Getter
public enum AccessLevelEnum {

    ADMIN("Administrador"),
    USER("Usuario"),
    DEFAULT("Default");

    private final String displayName;

    AccessLevelEnum(String displayName) {
        this.displayName = displayName;
    }

}
