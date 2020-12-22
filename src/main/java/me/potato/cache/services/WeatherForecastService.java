package me.potato.cache.services;

import io.quarkus.cache.CacheInvalidate;
import io.quarkus.cache.CacheInvalidateAll;
import io.quarkus.cache.CacheResult;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;

@ApplicationScoped
public class WeatherForecastService {

  @CacheResult(cacheName = "weather-cache")
  public String getDailyForecast(LocalDate date, String city) {
    try {
      Thread.sleep(2000L);
    } catch(InterruptedException e) {
      Thread.currentThread()
            .interrupt();
    }

    return date.getDayOfWeek()+" will be "+getDailyResult(date.getDayOfMonth()%4)+" in "+city;
  }

  @CacheInvalidate(cacheName = "weather-cache")
  public void removeCache(LocalDate date, String city) {

  }

  @CacheInvalidateAll(cacheName = "weather-cache")
  public void removeAllCache() {

  }


  private String getDailyResult(int dayOfMonthModuloFour) {
    switch(dayOfMonthModuloFour) {
      case 0:
        return "sunny";
      case 1:
        return "cloudy";
      case 2:
        return "chilly";
      case 3:
        return "rainy";
      default:
        throw new IllegalArgumentException();
    }
  }
}
