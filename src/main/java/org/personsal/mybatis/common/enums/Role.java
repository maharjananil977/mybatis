package org.personsal.mybatis.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Role {
    USER("USER"),
    ADMIN("ADMIN"),
    SUPER_ADMIN("SUPER_ADMIN");
    private final String name;

    public static Role getRoleByName(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        return Arrays.stream(Role.values()).filter(r -> r.name().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}
