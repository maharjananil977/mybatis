package org.personsal.mybatis.common.config;

import static org.personsal.mybatis.common.enums.Permission.ADMIN_DELETE;
import static org.personsal.mybatis.common.enums.Permission.ADMIN_EDIT;
import static org.personsal.mybatis.common.enums.Permission.ADMIN_READ;
import static org.personsal.mybatis.common.enums.Permission.ADMIN_WRITE;
import static org.personsal.mybatis.common.enums.Role.ADMIN;
import static org.personsal.mybatis.common.enums.Role.SUPER_ADMIN;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import lombok.RequiredArgsConstructor;
import org.personsal.mybatis.service.jwt.JwtAuthenticationFilter;
import org.personsal.mybatis.service.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final UserService userService;
  private final AuthenticationEntryPoint authEntryPoint;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            request ->
                request
                    .requestMatchers("/ping", "/api-docs", "/auth/**", "/register/**")
                    .permitAll()
                    .requestMatchers(GET, "/user/**")
                    .hasAnyRole(SUPER_ADMIN.name(), ADMIN.name())
                    .requestMatchers(GET, "/user/**")
                    .hasAnyAuthority(
                        ADMIN_READ.name(),
                        ADMIN_WRITE.name(),
                        ADMIN_EDIT.name(),
                        ADMIN_DELETE.name())
                    .anyRequest()
                    .authenticated())
        .httpBasic(basic -> basic.authenticationEntryPoint(authEntryPoint))
        .exceptionHandling(Customizer.withDefaults())
        .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
        .authenticationProvider(authenticationProvider())
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userService.userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
      throws Exception {
    return config.getAuthenticationManager();
  }
}
