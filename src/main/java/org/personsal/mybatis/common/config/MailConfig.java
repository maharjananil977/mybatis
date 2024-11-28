package org.personsal.mybatis.common.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/** created by: maharjananil created on: 11/28/2024 */
@Configuration
public class MailConfig {
  @Value("${config.mail.host}")
  private String host;
  @Value("${config.mail.port}")
  private int port;
  @Value("${config.mail.username}")
  private String username;
  @Value("${config.mail.password}")
  private String password;
  @Value("${config.mail.protocol}")
  private String protocol;
  @Value("${config.mail.smtp.auth}")
  private String auth;
  @Value("${config.mail.smtp.starttls}")
  private String starttls;
  @Value("${config.mail.smtp.debug}")
  private String debug;
  @Bean
  public JavaMailSender getJavaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost(host);
    mailSender.setPort(port);
    mailSender.setUsername(username);
    mailSender.setPassword(password);

    Properties props = mailSender.getJavaMailProperties();
    props.put("mail.transport.protocol", protocol);
    props.put("mail.smtp.auth", auth);
    props.put("mail.smtp.starttls.enable", starttls);
    props.put("mail.debug", debug);

    return mailSender;
  }
}
