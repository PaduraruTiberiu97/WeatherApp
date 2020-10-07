package ro.pentatraining.weatherapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ro.pentatraining.weatherapp.R;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private Button registerButton;
    private EditText edtEmail;
    private EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        bindUI();

        final SharedPreferences sharedPreferences = getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE);
        final Boolean isLoggedIn = sharedPreferences.getBoolean("ISLOGGEDIN", false);
        verifyIfLoggedIn(isLoggedIn);
        final String requiredEmail = sharedPreferences.getString("EMAIL", "DEFAULT_EMAIL");
        final String requiredPassword = sharedPreferences.getString("PASSWORD", "DEFAULT_PASSWORD");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                if (email.equals(requiredEmail) && password.equals(requiredPassword)) {
                    sharedPreferences.edit().putBoolean("ISLOGGEDIN", true).apply();
                    Intent homeScreenIntent = new Intent(MainActivity.this, HomeScreenActivity.class);
                    startActivity(homeScreenIntent);
                } else {
                    Toast.makeText(MainActivity.this, "Email adress or password is incorrect",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        setRegisterButton();
    }

    private void verifyIfLoggedIn(boolean myBoolean) {
        if (myBoolean) {
            Intent intent = new Intent(MainActivity.this, HomeScreenActivity.class);
            startActivity(intent);
        }
    }

    private void bindUI() {
        loginButton = findViewById(R.id.btn_login);
        registerButton = findViewById(R.id.btn_register);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
    }

    private void setRegisterButton() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerScreenIntent = new Intent(MainActivity.this, RegisterScreenActivity.class);
                startActivity(registerScreenIntent);
                finish();
            }
        });
    }
}
