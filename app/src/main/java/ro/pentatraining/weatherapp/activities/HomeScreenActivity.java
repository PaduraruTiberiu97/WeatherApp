package ro.pentatraining.weatherapp.activities;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ro.pentatraining.weatherapp.R;

public class HomeScreenActivity extends AppCompatActivity {

    private Button weatherButton;
    private Button myPlacesButton;
    private Button mapsButton;
    private Button myProfileButton;
    private Button logoutButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_activity);

        bindUI();
        setClickListeners();
    }

    private void bindUI() {
        weatherButton = findViewById(R.id.btn_weather);
        myPlacesButton = findViewById(R.id.btn_myPlaces);
        mapsButton = findViewById(R.id.btn_maps);
        myProfileButton = findViewById(R.id.btn_myProfile);
        logoutButton = findViewById(R.id.btn_logout);
    }

    private void setClickListeners() {
        weatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent weatherIntent = new Intent(HomeScreenActivity.this, SearchScreenActivity.class);
                startActivity(weatherIntent);
            }
        });

        myPlacesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myPlacesIntent = new Intent(HomeScreenActivity.this, MyPlacesActivity.class);
                startActivity(myPlacesIntent);
            }
        });

        mapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.toast_maps),
                        Toast.LENGTH_SHORT).show();
            }
        });

        myProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myProfileIntent=new Intent(HomeScreenActivity.this,MyProfileActivity.class);
                startActivity(myProfileIntent);
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SharedPreferences sharedPreferences = getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE);
                sharedPreferences.edit().putBoolean("ISLOGGEDIN", false).apply();
                Intent logoutIntent = new Intent(HomeScreenActivity.this, MainActivity.class);
                startActivity(logoutIntent);
            }
        });


    }
}