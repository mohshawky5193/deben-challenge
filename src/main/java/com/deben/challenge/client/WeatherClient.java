package com.deben.challenge.client;

import com.deben.challenge.model.City;
import com.deben.challenge.model.WeatherData;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="WeatherClient",url="https://api.openweathermap.org/")
public interface WeatherClient {

  @GetMapping("/data/2.5/weather")
  WeatherData getWeatherData(@RequestParam("lat") Double lat,@RequestParam("lon") Double lon,@RequestParam("units") String units,@RequestParam("appId") String appId);
  @GetMapping("/geo/1.0/direct")
  List<City> getCities(@RequestParam("q") String city,@RequestParam("appId") String appId);
}
