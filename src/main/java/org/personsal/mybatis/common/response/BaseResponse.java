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

  public BaseResponse<T> toBaseResponse(T request) {
    return BaseResponse.<T>builder().status(HttpStatus.OK).data(request).build();
  }

  public BaseResponse<T> toCreatedBaseResponse(T request) {
    return BaseResponse.<T>builder().status(HttpStatus.CREATED).data(request).build();
  }
}
