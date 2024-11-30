package org.personsal.mybatis.common.exceptionhandler;

import org.personsal.mybatis.common.exceptionhandler.exceptions.ApiException;
import org.personsal.mybatis.common.exceptionhandler.exceptions.KeyMissingException;
import org.personsal.mybatis.common.exceptionhandler.exceptions.NotFoundException;
import org.personsal.mybatis.common.response.ExceptionResponse;
import org.personsal.mybatis.utils.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/** created by: maharjananil created on: 11/30/2024 */
@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ExceptionResponse> handleApiRequestException(ApiException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(
                new ExceptionResponse(exception.getMessage(), status, DateUtils.getTimestamp()), status);
    }

    @ExceptionHandler({NotFoundException.class, KeyMissingException.class})
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(
                new ExceptionResponse(exception.getMessage(), status, DateUtils.getTimestamp()), status);
    }
}

