package org.personsal.mybatis.common.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> implements Response {
    private HttpStatus status;
    private T data;
    private long timestamp;
}
