package org.personsal.mybatis.domain.notification;

import lombok.Builder;
import lombok.Data;
import org.personsal.mybatis.common.request.Request;

import java.util.List;

/** created by: maharjananil created on: 11/28/2024 */
@Data
@Builder
public class NotificationRequest implements Request {
  private String title;
  private String content;
  private List<String> recipients;
  private String sender;
}
