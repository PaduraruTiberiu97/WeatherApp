package ro.pentatraining.weatherapp.holders;

public class Main {
    private double temp;

    public String getTemp() {
        String finalTemp = "";
        double celsiusTemp = temp - 272.15;
        int roundedTemp = (int) celsiusTemp;
        finalTemp = roundedTemp + "°C";
        return finalTemp;
    }

}