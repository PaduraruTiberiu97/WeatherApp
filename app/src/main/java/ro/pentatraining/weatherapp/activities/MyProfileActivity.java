package ro.pentatraining.weatherapp.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ro.pentatraining.weatherapp.R;

public class MyProfileActivity extends AppCompatActivity {
    private TextView email;
    private TextView name;
    private TextView birthday;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile_activity);
        addImage();
        bindUI();
        final SharedPreferences sharedPreferences = getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE);
        final String sharedPrefsEmail = sharedPreferences.getString("EMAIL", "DEFAULT_EMAIL");
        final String sharedPrefsName = sharedPreferences.getString("NAME", "DEFAULT_NAME");
        final String sharedPrefsBirthday = sharedPreferences.getString("BIRTHDAY", "DEFAULT_BIRTHDAY");
        email.setText(sharedPrefsEmail);
        name.setText(sharedPrefsName);
        birthday.setText(sharedPrefsBirthday);
    }
    private void bindUI(){
        email=findViewById(R.id.txt_email_input_profile);
        name=findViewById(R.id.txt_name_input_profile);
        birthday=findViewById(R.id.txt_birthday_input_profile);
    }
    private void addImage(){
        ImageView imgTest = findViewById(R.id.imgView_profile);
        Glide.with(this)
                .load("https://images-cdn.9gag.com/photo/aR3GzVB_700b.jpg")
                .into(imgTest);
    }
}
