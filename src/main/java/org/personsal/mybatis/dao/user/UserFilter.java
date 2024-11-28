package org.personsal.mybatis.dao.user;

import lombok.Builder;
import lombok.Data;
import org.personsal.mybatis.common.filter.Filter;

/** created by: maharjananil created on: 11/28/2024 */
@Data
@Builder
public class UserFilter implements Filter {
    private String email;
    private boolean verified;
}
