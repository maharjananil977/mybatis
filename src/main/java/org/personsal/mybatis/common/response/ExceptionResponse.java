package org.personsal.mybatis.common.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

/** created by: maharjananil created on: 11/30/2024 */
@Builder
public record ExceptionResponse(String message, HttpStatus status, long timestamp)
        implements Response {}

