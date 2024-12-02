package org.personsal.mybatis.common.exceptionhandler;

import org.personsal.mybatis.common.exceptionhandler.exceptions.ApiException;
import org.personsal.mybatis.common.exceptionhandler.exceptions.InvalidException;
import org.personsal.mybatis.common.exceptionhandler.exceptions.KeyMissingException;
import org.personsal.mybatis.common.exceptionhandler.exceptions.NotFoundException;
import org.personsal.mybatis.common.response.ExceptionResponse;
import org.personsal.mybatis.utils.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/** created by: maharjananil created on: 11/30/2024 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler({ApiException.class, InvalidException.class})
  public ResponseEntity<ExceptionResponse> handleApiRequestException(Exception exception) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    return new ResponseEntity<>(
        new ExceptionResponse(exception.getMessage(), status, DateUtils.getTimestamp()), status);
  }

  @ExceptionHandler({NotFoundException.class, KeyMissingException.class})
  public ResponseEntity<ExceptionResponse> handleValidationException(Exception exception) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    return new ResponseEntity<>(
        new ExceptionResponse(exception.getMessage(), status, DateUtils.getTimestamp()), status);
  }

  @ExceptionHandler({InternalError.class,Exception.class})
  public ResponseEntity<ExceptionResponse> handleInternalException(Exception exception) {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    return new ResponseEntity<>(
            new ExceptionResponse(exception.getMessage(), status, DateUtils.getTimestamp()), status);
  }
}
