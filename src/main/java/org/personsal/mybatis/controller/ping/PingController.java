package org.personsal.mybatis.controller.ping;

import org.personsal.mybatis.common.response.BaseResponse;
import org.personsal.mybatis.domain.ping.PingRequest;
import org.personsal.mybatis.domain.ping.PingResponse;
import org.personsal.mybatis.utils.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** created by: maharjananil created on: 11/26/2024 */
@RestController
@RequestMapping("ping")
public class PingController {
  @GetMapping
  public ResponseEntity<BaseResponse<String>> getPing() {
    return ResponseEntity.ok(
        BaseResponse.<String>builder()
            .status(HttpStatus.OK)
            .data("Pong")
            .timestamp(DateUtils.getTimestamp())
            .build());
  }

  @PostMapping
  public ResponseEntity<BaseResponse<PingResponse>> postPing(@RequestBody PingRequest request) {
    return ResponseEntity.ok(
        BaseResponse.<PingResponse>builder()
            .status(HttpStatus.OK)
            .data(new PingResponse("Response: " + request.message()))
            .timestamp(DateUtils.getTimestamp())
            .build());
  }
}
