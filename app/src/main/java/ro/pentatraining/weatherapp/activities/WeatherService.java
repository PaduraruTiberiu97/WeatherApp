package ro.pentatraining.weatherapp.activities;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ro.pentatraining.weatherapp.api.ApiConstants;
import ro.pentatraining.weatherapp.holders.WeatherResponse;

public interface WeatherService {
    @GET(ApiConstants.WEATHER)
    Call<WeatherResponse>getWeatherByCityName(@Query("APIKEY") String appKey,
                                              @Query("q") String cityName);
}
