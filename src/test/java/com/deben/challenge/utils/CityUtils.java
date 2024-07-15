package com.deben.challenge.utils;

import com.deben.challenge.model.City;

public class CityUtils {

  public static City cairo() {
    City city = new City();
    city.setName("Cairo");
    city.setLat(30.0444);
    city.setLon(31.2357);
    return city;
  }
}
