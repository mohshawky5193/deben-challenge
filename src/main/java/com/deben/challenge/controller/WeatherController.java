package com.deben.challenge.controller;

import com.deben.challenge.model.WeatherData;
import com.deben.challenge.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController {

  private final WeatherService weatherService;
  @GetMapping("/api/weather/{city}")
  public WeatherData getWeatherData(@PathVariable("city") String city){
    return weatherService.getWeatherData(city);
  }

  @GetMapping("/api/report/{city}")
  public String getReport(@PathVariable("city") String city){
    return weatherService.getReport(city);
  }
}
