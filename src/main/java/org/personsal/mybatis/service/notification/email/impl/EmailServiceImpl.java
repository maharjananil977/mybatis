package org.personsal.mybatis.service.notification.email.impl;

import lombok.RequiredArgsConstructor;
import org.personsal.mybatis.domain.notification.NotificationRequest;
import org.personsal.mybatis.domain.notification.NotificationResponse;
import org.personsal.mybatis.service.notification.email.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/** created by: maharjananil created on: 11/28/2024 */
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
  private final JavaMailSender mailSender;

  @Override
  public NotificationResponse send(NotificationRequest request) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("doNotReply@anil.com");
    message.setTo(request.getRecipients().get(0));
    message.setText(request.getContent());
    message.setSubject(request.getTitle());
    try {
      mailSender.send(message);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return new NotificationResponse("");
  }
}
