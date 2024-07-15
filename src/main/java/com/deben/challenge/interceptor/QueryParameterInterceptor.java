package com.deben.challenge.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;

public class QueryParameterInterceptor implements RequestInterceptor {

  @Value("${app.appId}")
  private String appId;
  @Override
  public void apply(RequestTemplate requestTemplate) {
    requestTemplate.query("appId",appId);
  }
}
