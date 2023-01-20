package io.github.wemersonwalcley.fitness_tracker.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

@JsonFilter("attributeFilter")
public enum AccessLevelEnum {

    ADMIN("Administrador"),
    USER("Usuario");

    private String displayName;

    AccessLevelEnum(String displayName) {
        this.displayName = displayName;
    }

    @JsonCreator
    public static AccessLevelEnum forValue(String value) {
        return AccessLevelEnum.valueOf(value);
    }

    @JsonValue
    public Map<String, String > jsonValue() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", this.name());
        map.put("displayName", this.displayName);
        return map;
    }

}
