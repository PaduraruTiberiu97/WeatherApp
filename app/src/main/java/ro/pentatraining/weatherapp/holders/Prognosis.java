package ro.pentatraining.weatherapp.holders;

import com.google.gson.annotations.SerializedName;

public class Prognosis {
    @SerializedName("name")
    private String cityName;
    @SerializedName("description")
    private String weatherDescription;
    private String temp;
    @SerializedName("maxtemp")
    private String maxTemp;
    @SerializedName("mintemp")
    private String minTemp;

    public Prognosis(String cityName, String weatherDescription, String temp, String maxTemp, String minTemp) {
        this.cityName = cityName;
        this.weatherDescription = weatherDescription;
        this.temp = temp;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    public String getName() {
        return cityName;
    }

    public String getDescription() {
        return weatherDescription;
    }

    public String getTemp() {
        return temp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }
}
