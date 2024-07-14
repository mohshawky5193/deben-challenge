package com.deben.challenge.service;

import com.deben.challenge.client.WeatherClient;
import com.deben.challenge.model.City;
import com.deben.challenge.model.WeatherData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@Service
public class WeatherService {

  private final WeatherClient weatherClient;
  private final ReportService reportService;
  public WeatherData getWeatherData(String city){
    City cityFound = weatherClient.getCities(city,"5b652be827fd22fd331279372432f7e0").stream().findFirst().get();
    return  weatherClient.getWeatherData(cityFound.getLat(),cityFound.getLon(),"metric","5b652be827fd22fd331279372432f7e0");
  }

  public String getReport(String city){
    WeatherData weatherData = getWeatherData(city);
    reportService.generateReport(city,weatherData);
    return "WeatherReport-"+ city+".pdf";
  }
}
