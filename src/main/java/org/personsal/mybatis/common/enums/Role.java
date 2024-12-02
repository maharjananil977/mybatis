package org.personsal.mybatis.common.enums;

import static org.personsal.mybatis.common.enums.Permission.ADMIN_DELETE;
import static org.personsal.mybatis.common.enums.Permission.ADMIN_EDIT;
import static org.personsal.mybatis.common.enums.Permission.ADMIN_READ;
import static org.personsal.mybatis.common.enums.Permission.ADMIN_WRITE;
import static org.personsal.mybatis.common.enums.Permission.USER_DELETE;
import static org.personsal.mybatis.common.enums.Permission.USER_EDIT;
import static org.personsal.mybatis.common.enums.Permission.USER_READ;
import static org.personsal.mybatis.common.enums.Permission.USER_WRITE;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
@AllArgsConstructor
public enum Role {
  USER("USER", Set.of(USER_WRITE,USER_READ,USER_EDIT,USER_DELETE)),
  ADMIN("ADMIN", Set.of(ADMIN_EDIT, ADMIN_READ, ADMIN_WRITE, ADMIN_DELETE)),
  SUPER_ADMIN("SUPER_ADMIN", Set.of(ADMIN_EDIT, ADMIN_READ, ADMIN_WRITE, ADMIN_DELETE)),
  ;
  private final String name;
  private final Set<Permission> permissions;

  public static Role getRoleByName(String name) {
    if (name == null || name.isEmpty()) {
      return null;
    }
    return Arrays.stream(Role.values())
        .filter(r -> r.name().equalsIgnoreCase(name))
        .findFirst()
        .orElse(null);
  }

  public List<SimpleGrantedAuthority> getGrantedAuthorities() {
    List<SimpleGrantedAuthority> authorities = new java.util.ArrayList<>(getPermissions().stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission() ))
            .toList());
    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + this.name);
    authorities.add(simpleGrantedAuthority);
    return authorities;
  }
}
