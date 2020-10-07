package ro.pentatraining.weatherapp.holders;

import java.util.ArrayList;

public class List {
    public String dt_txt;
    public java.util.List<Weather> weather = new ArrayList<Weather>();
    public Main main;

    public java.util.List<Weather> getWeather() {
        return weather;
    }

    public Main getMain() {
        return main;
    }

    public String getDt_txt() {
        String fullDate = dt_txt;
        String[] splitDate = fullDate.split(" ");
        String onlyDate = splitDate[0];
        String monthOfYear = "";
        String[] splitDate2 = onlyDate.split("-");
        String dayOfMonthValue="";
        String dayOfMonth=splitDate2[2];

        if(dayOfMonth.charAt(0)=='0'){
            dayOfMonthValue= dayOfMonth.substring(1);}
            else{
                dayOfMonthValue=splitDate2[2];
        }

        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December"};

        switch (splitDate2[1]) {
            case "01":
                monthOfYear = months[0];
                break;
            case "02":
                monthOfYear = months[1];
                break;
            case "03":
                monthOfYear = months[2];
                break;
            case "04":
                monthOfYear = months[3];
                break;
            case "05":
                monthOfYear = months[4];
                break;
            case "06":
                monthOfYear = months[5];
                break;
            case "07":
                monthOfYear = months[6];
                break;
            case "08":
                monthOfYear = months[7];
                break;
            case "09":
                monthOfYear = months[8];
                break;
            case "10":
                monthOfYear = months[9];
                break;
            case "11":
                monthOfYear = months[10];
                break;
            case "12":
                monthOfYear = months[11];
                break;
        }

        String finalDate =dayOfMonthValue + " " + monthOfYear;

        return finalDate;
    }
}