package com.deben.challenge.model;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class WeatherData {

  private List<Weather> weather;
  private MainData main;
}
