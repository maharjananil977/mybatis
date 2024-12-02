package org.personsal.mybatis.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** created by: maharjananil created on: 12/02/2024 */
@Getter
@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),
    ADMIN_DELETE("admin:delete"),
    ADMIN_EDIT("admin:edit"),

    USER_READ("user:read"),
    USER_WRITE("user:write"),
    USER_EDIT("user:edit"),
    USER_DELETE("user:delete"),;

    private final String permission;

}
