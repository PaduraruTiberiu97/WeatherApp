package ro.pentatraining.weatherapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ro.pentatraining.weatherapp.R;
import ro.pentatraining.weatherapp.api.ApiConstants;
import ro.pentatraining.weatherapp.data.CityData;
import ro.pentatraining.weatherapp.database.AppDatabase;
import ro.pentatraining.weatherapp.holders.WeatherResponse;

public class ForecastActivity extends AppCompatActivity {
    private RetrofitClient retrofit;
    private String searchedValue;
    private TextView searchedCity;
    private TextView todayTemp;
    private TextView tomorowTemp;
    private TextView oneDayAfterTomorowTemp;
    private TextView twoDaysAfterTomorowTemp;
    private TextView todayDescription;
    private TextView tomorowDescription;
    private TextView oneDayAfterTomorowDescription;
    private TextView twoDaysAfterTomorowDescription;
    private TextView oneDayAfterTomorow;
    private TextView twoDaysAfterTomorow;
    private Button addToFavorites;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forecast_activity);

        bindUI();
        setClickListeners();

        searchedValue = getIntent().getStringExtra("searchedCity");
        retrofit = RetrofitClient.getInstance();
        loadWeather();
    }


    public void setAlertDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).setMessage("Do you want to add location to your places?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addCity();
                        Toast.makeText(ForecastActivity.this, "Location added", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel",null).show();
    }

    public void bindUI() {
        addToFavorites = findViewById(R.id.btn_favorites);
        oneDayAfterTomorow = findViewById(R.id.txt_oneDayAfterTomorow);
        twoDaysAfterTomorow = findViewById(R.id.txt_twoDaysAfterTomorow);
        searchedCity = findViewById(R.id.txt_city_forecast);
        todayTemp = findViewById(R.id.txt_today_temp);
        tomorowTemp = findViewById(R.id.txt_tomorow_temp);
        oneDayAfterTomorowTemp = findViewById(R.id.txt_oneDayAfterTomorow_temp);
        twoDaysAfterTomorowTemp = findViewById(R.id.txt_twoDaysAfterTomorow_temp);
        todayDescription = findViewById(R.id.txt_today_description);
        tomorowDescription = findViewById(R.id.txt_tomorow_description);
        oneDayAfterTomorowDescription = findViewById(R.id.txt_oneDayAfterTomorow_description);
        twoDaysAfterTomorowDescription = findViewById(R.id.txt_twoDaysAfterTomorow_description);
    }

    public void loadWeather() {
        Call<WeatherResponse> call = retrofit.getWeatherService().getWeatherByCityName(ApiConstants.APP_KEY, searchedValue);
        call.enqueue(new Callback<WeatherResponse>() {

            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                searchedCity.setText(response.body().city.getCityName());
                todayTemp.setText(response.body().getList().get(0).getMain().getTemp());
                todayDescription.setText(response.body().getList().get(0).getWeather().get(0).getMain());
                tomorowTemp.setText(response.body().getList().get(8).getMain().getTemp());
                tomorowDescription.setText(response.body().getList().get(8).getWeather().get(0).getMain());
                oneDayAfterTomorow.setText(response.body().getList().get(16).getDt_txt());
                oneDayAfterTomorowTemp.setText(response.body().getList().get(16).getMain().getTemp());
                oneDayAfterTomorowDescription.setText(response.body().getList().get(16).getWeather().get(0).getMain());
                twoDaysAfterTomorow.setText(response.body().getList().get(24).getDt_txt());
                twoDaysAfterTomorowTemp.setText(response.body().getList().get(24).getMain().getTemp());
                twoDaysAfterTomorowDescription.setText(response.body().getList().get(24).getWeather().get(0).getMain());

            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) { Intent intent=new Intent(ForecastActivity.this,SearchScreenActivity.class);
                startActivity(intent);
                Toast.makeText(ForecastActivity.this, "Error", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    private void setClickListeners() {
        addToFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlertDialog();
            }
        });
    }

    private void addCity(){
        String a,b,c;
        a=searchedCity.toString();
        b=todayDescription.toString();
        c=todayTemp.toString();
        CityData city=new CityData(a,b,c);
        AppDatabase.getInstance().cityDao().insertAll(city);
    }
}
