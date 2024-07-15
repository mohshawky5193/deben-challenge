package com.deben.challenge.service;

import static com.deben.challenge.service.WeatherService.UNITS_METRIC;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.deben.challenge.client.WeatherClient;
import com.deben.challenge.exception.CityNotFoundException;
import com.deben.challenge.model.City;
import com.deben.challenge.model.WeatherData;
import com.deben.challenge.utils.CityUtils;
import com.deben.challenge.utils.WeatherDataUtils;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

  @InjectMocks
  private WeatherService weatherService;

  @Mock
  private WeatherClient weatherClient;

  @Mock
  private ReportService reportService;


  @Test
  public void getWeatherDataTest() {
    //Arrange
    City cairo = CityUtils.cairo();
    doReturn(List.of(cairo)).when(weatherClient).getCities("Cairo");
    WeatherData expected = WeatherDataUtils.weatherDataCairo();
    doReturn(expected).when(weatherClient).getWeatherData(cairo.getLat(),cairo.getLon(),UNITS_METRIC);
    //Act
    WeatherData actual = weatherService.getWeatherData("Cairo");
    //Assert
    assertEquals(actual,expected);
    verify(weatherClient).getCities("Cairo");
    verify(weatherClient).getWeatherData(cairo.getLat(), cairo.getLon(), UNITS_METRIC);
  }

  @Test
  public void getReportTest() {
    //Arrange
    City cairo = CityUtils.cairo();
    doReturn(List.of(cairo)).when(weatherClient).getCities("Cairo");
    String expected = "WeatherReport-Cairo.pdf";
    WeatherData weatherDataCairo = WeatherDataUtils.weatherDataCairo();
    doReturn(weatherDataCairo).when(weatherClient).getWeatherData(cairo.getLat(),cairo.getLon(),UNITS_METRIC);
    doReturn(expected).when(reportService).generateReport("Cairo",weatherDataCairo);
    //Act
    String actual= weatherService.getReport("Cairo");
    //Assert
    assertEquals(actual,expected);
    verify(weatherClient).getCities("Cairo");
    verify(weatherClient).getWeatherData(cairo.getLat(), cairo.getLon(), UNITS_METRIC);
    verify(reportService).generateReport("Cairo",weatherDataCairo);
  }

  @Test
  public void getWeatherDataTestCityNotFound() {
    //Arrange
    doReturn(List.of()).when(weatherClient).getCities(anyString());
    //Act
    //Assert
    assertThrows(CityNotFoundException.class,() -> weatherService.getWeatherData("Cairo"));
    verify(weatherClient).getCities("Cairo");
    verify(weatherClient,never()).getWeatherData(anyDouble(), anyDouble(), anyString());
  }
}
