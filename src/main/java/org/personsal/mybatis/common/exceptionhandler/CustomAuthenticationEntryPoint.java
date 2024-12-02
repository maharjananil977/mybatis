package org.personsal.mybatis.common.exceptionhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.personsal.mybatis.common.response.ExceptionResponse;
import org.personsal.mybatis.utils.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

/** created by: maharjananil created on: 12/03/2024 */
@Component("customAuthenticationEntryPoint")
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException, ServletException {
    ExceptionResponse re;
    if (authException instanceof AccountExpiredException) {
      re =
          new ExceptionResponse(
              "Account Expired", HttpStatus.UNAUTHORIZED, DateUtils.getTimestamp());
    } else if (authException instanceof BadCredentialsException) {
      re =
          new ExceptionResponse(
              "Invalid username or password", HttpStatus.UNAUTHORIZED, DateUtils.getTimestamp());
    } else if (authException instanceof LockedException) {
      re =
          new ExceptionResponse(
              "Account Locked", HttpStatus.UNAUTHORIZED, DateUtils.getTimestamp());
    } else if (authException instanceof CredentialsExpiredException) {
      re =
          new ExceptionResponse(
              "Credential Expired", HttpStatus.UNAUTHORIZED, DateUtils.getTimestamp());
    } else {
      re = new ExceptionResponse("Unauthorized", HttpStatus.UNAUTHORIZED, DateUtils.getTimestamp());
    }
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    OutputStream responseStream = response.getOutputStream();
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(responseStream, re);
    responseStream.flush();
  }
}
