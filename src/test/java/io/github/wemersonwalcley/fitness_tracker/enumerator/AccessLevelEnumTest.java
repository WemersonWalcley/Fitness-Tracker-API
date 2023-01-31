package io.github.wemersonwalcley.fitness_tracker.enumerator;

import io.github.wemersonwalcley.fitness_tracker.enumeration.AccessLevelEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccessLevelEnumTest {

    @Test
    public void testGetDisplayName() {
        assertEquals("Administrador", AccessLevelEnum.ADMIN.getDisplayName());
        assertEquals("Usuario", AccessLevelEnum.USER.getDisplayName());
        assertEquals("Default", AccessLevelEnum.DEFAULT.getDisplayName());
    }

}
