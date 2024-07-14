package com.deben.challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MainData {

  private Double temp;
  @JsonProperty("temp_max")
  private Double tempMax;

  @JsonProperty("temp_min")
  private Double tempMin;
}
