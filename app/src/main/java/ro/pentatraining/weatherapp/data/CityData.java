package ro.pentatraining.weatherapp.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "cities")
public class CityData {
    @PrimaryKey

    @ColumnInfo(name = "city_name")
    @NonNull
    private String cityName;

    @ColumnInfo(name = "weather_description")
    private String weatherDescription;

    @ColumnInfo(name = "temperature")
    private String temp;

    public String getTemp() {
        return temp;
    }

    public String getCityName() {
        return cityName;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public CityData(String cityName, String weatherDescription, String temp) {
        this.cityName = cityName;
        this.weatherDescription = weatherDescription;
        this.temp = temp;
    }
}
