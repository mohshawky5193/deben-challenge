package com.deben.challenge.service;

import com.deben.challenge.client.WeatherClient;
import com.deben.challenge.exception.CityNotFoundException;
import com.deben.challenge.model.City;
import com.deben.challenge.model.WeatherData;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@Service
public class WeatherService {

  public static final String UNITS_METRIC = "metric";
  private final WeatherClient weatherClient;
  private final ReportService reportService;
  public WeatherData getWeatherData(String city){
    Optional<City> cityOptional = weatherClient.getCities(city).stream().findFirst();
    if(cityOptional.isEmpty()){
      throw new CityNotFoundException();
    }else{
      City cityFound = cityOptional.get();
      return  weatherClient.getWeatherData(cityFound.getLat(),cityFound.getLon(),UNITS_METRIC);
    }
  }

  public String getReport(String city){
    WeatherData weatherData = getWeatherData(city);
    return reportService.generateReport(city,weatherData);
  }
}
