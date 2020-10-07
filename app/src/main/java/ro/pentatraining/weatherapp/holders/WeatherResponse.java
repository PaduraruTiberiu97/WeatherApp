package ro.pentatraining.weatherapp.holders;

import java.util.ArrayList;

public class WeatherResponse {

    public City city;
    public java.util.List<List> list = new ArrayList<List>();

    public City getCity() {
        return city;
    }

    public java.util.List<List> getList() {
        return list;
    }
}
