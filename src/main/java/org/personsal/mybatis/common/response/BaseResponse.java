package org.personsal.mybatis.common.response;

import lombok.*;
import org.personsal.mybatis.utils.DateUtils;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> implements Response {
  private HttpStatus status;
  private T data;
  private long timestamp;
  private int total;
  private int pageSize;
  private int currentPage;

  public BaseResponse<T> toBaseResponse(T request) {
    return BaseResponse.<T>builder()
        .status(HttpStatus.OK)
        .data(request)
        .timestamp(DateUtils.getTimestamp())
        .build();
  }

  public BaseResponse<T> toBaseResponse(T request, int total, int pageSize, int currentPage) {
    return BaseResponse.<T>builder()
        .status(HttpStatus.OK)
        .data(request)
        .total(total)
        .pageSize(pageSize)
        .currentPage(currentPage)
        .timestamp(DateUtils.getTimestamp())
        .build();
  }

  public BaseResponse<T> toCreatedBaseResponse(T request) {
    return BaseResponse.<T>builder()
        .status(HttpStatus.CREATED)
        .data(request)
        .timestamp(DateUtils.getTimestamp())
        .build();
  }
}
