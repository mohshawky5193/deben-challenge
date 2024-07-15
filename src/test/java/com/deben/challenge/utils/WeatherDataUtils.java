package com.deben.challenge.utils;

import com.deben.challenge.model.MainData;
import com.deben.challenge.model.Weather;
import com.deben.challenge.model.WeatherData;
import java.util.List;

public class WeatherDataUtils {
  public static WeatherData weatherDataCairo(){
    WeatherData weatherData = new WeatherData();
    Weather weather = new Weather();
    weather.setDescription("Sunny");
    weatherData.setWeather(List.of(weather));
    MainData mainData = new MainData();
    mainData.setTemp(44.0);
    mainData.setTempMax(45.0);
    mainData.setTempMin(35.0);
    weatherData.setMain(mainData);
    return weatherData;
  }

}
