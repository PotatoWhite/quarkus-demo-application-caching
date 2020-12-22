package me.potato.cache.services.controllers;

import lombok.RequiredArgsConstructor;
import me.potato.cache.services.WeatherForecast;
import me.potato.cache.services.WeatherForecastService;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Path("/weather")
public class WeatherForecastResource {

  private final WeatherForecastService service;

  @GET
  public WeatherForecast getForecast(@QueryParam String city, @QueryParam long daysInFuture) {
    Long executionStart = System.currentTimeMillis();
    List<String> dailyForecasts = Arrays.asList(
            service.getDailyForecast(LocalDate.now()
                                              .plusDays(daysInFuture), city),
            service.getDailyForecast(LocalDate.now()
                                              .plusDays(daysInFuture+1L), city),
            service.getDailyForecast(LocalDate.now()
                                              .plusDays(daysInFuture+2L), city)
                                               );

    Long executionEnd = System.currentTimeMillis();
    return new WeatherForecast(dailyForecasts, executionEnd-executionStart);
  }

  @DELETE
  public void removeCache(@QueryParam String city, @QueryParam long daysInFuture) {

    service.removeCache(LocalDate.now()
                                 .plusDays(daysInFuture), city);
    service.removeCache(LocalDate.now()
                                 .plusDays(daysInFuture+1l), city);
    service.removeCache(LocalDate.now()
                                 .plusDays(daysInFuture+2l), city);
  }
}
