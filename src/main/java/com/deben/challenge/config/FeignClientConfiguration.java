package com.deben.challenge.config;

import com.deben.challenge.interceptor.QueryParameterInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfiguration {

  @Bean
  public RequestInterceptor requestInterceptor(){
    return new QueryParameterInterceptor();
  }
}
