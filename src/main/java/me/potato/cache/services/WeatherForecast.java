package me.potato.cache.services;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class WeatherForecast {
  private List<String> dailyForecasts;
  private Long         executionTimeInMs;
}
