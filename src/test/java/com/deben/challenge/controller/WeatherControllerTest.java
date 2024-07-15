package com.deben.challenge.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.deben.challenge.config.SecurityConfiguration;
import com.deben.challenge.exception.CityNotFoundException;
import com.deben.challenge.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@AutoConfigureJson
@Import(SecurityConfiguration.class)
public class WeatherControllerTest {

  @MockBean
  private WeatherService weatherService;

  @Autowired
  private MockMvc mockMvc;


  @Test
  public void testGetWeather() throws Exception {

    mockMvc.perform(get("/api/weather/Cairo")).andExpect(status().isOk());
    weatherService.getWeatherData("Cairo");
  }

  @Test
  public void testGetReportUnauthorized() throws Exception {

    mockMvc.perform(get("/api/report/Cairo")).andExpect(status().isUnauthorized());
  }

  @Test
  public void testGetReportWithJwt() throws Exception {

    mockMvc.perform(get("/api/report/Cairo").with(jwt())).andExpect(status().isOk());
  }

  @Test
  public void testGetWeatherWithNonExistingCity() throws Exception {

    doThrow(new CityNotFoundException()).when(weatherService).getWeatherData(anyString());
    mockMvc.perform(get("/api/weather/non_existing")).andExpect(status().isBadRequest());
  }
}
