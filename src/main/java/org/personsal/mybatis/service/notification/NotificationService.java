package org.personsal.mybatis.service.notification;

import org.personsal.mybatis.common.request.Request;
import org.personsal.mybatis.common.response.Response;

/** created by: maharjananil created on: 11/28/2024 */
public interface NotificationService<T extends Request, R extends Response> {
  R send(T request);
}
