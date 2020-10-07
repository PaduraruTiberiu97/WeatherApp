package ro.pentatraining.weatherapp.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import ro.pentatraining.weatherapp.R;

public class RegisterScreenActivity extends AppCompatActivity {
    private EditText userName;
    private EditText password;
    private EditText confirmPassword;
    private EditText email;
    private Button registerButton;
    private EditText birthdayEditText;
    private int year, month, day;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen_layout);

        bindUI();
        setRegisterButton();
        setBirthday();
    }

    private void bindUI() {
        registerButton = findViewById(R.id.btn_register_registerLayout);
        birthdayEditText = findViewById(R.id.edt_birthday_registerLayout);
        userName = findViewById(R.id.edt_username_registerLayout);
        password = findViewById(R.id.edt_password_registerLayout);
        confirmPassword = findViewById(R.id.edt_confirmPassword_registerLayout);
        email = findViewById(R.id.edt_email_registerLayout);
    }

    private void setRegisterButton() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUser = userName.getText().toString();
                String newPassword = password.getText().toString();
                String newConfirmPassword = confirmPassword.getText().toString();
                String newEmail = email.getText().toString();
                String newBirthday = birthdayEditText.getText().toString();
                Intent registerScreenIntent = new Intent(RegisterScreenActivity.
                        this, HomeScreenActivity.class);

                if (newPassword.equals(newConfirmPassword) && newUser.length() >= 6
                        && newEmail.length() >= 6 && newBirthday.length()>5 && newPassword.length()>=6) {
                    SharedPreferences sharedPreferences = getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("NAME", newUser);
                    editor.putString("EMAIL", newEmail);
                    editor.putString("BIRTHDAY", newBirthday);
                    editor.putString("PASSWORD", newPassword);
                    editor.putBoolean("ISLOGGEDIN", true);
                    editor.apply();

                    startActivity(registerScreenIntent);

                }else if(newPassword.length()<6) {
                    Toast.makeText(RegisterScreenActivity.this, "Password must have at least 6 characters", Toast.LENGTH_SHORT).show();
                }
                else if (!newConfirmPassword.equals(newPassword)) {
                    Toast.makeText(RegisterScreenActivity.this, "Passwords don't match",
                            Toast.LENGTH_SHORT).show();
                } else if (newUser.length() <6) {
                    Toast.makeText(RegisterScreenActivity.this, "Username must have at least 6 characters", Toast.LENGTH_SHORT).show();
                } else if (newEmail.length() < 6) {
                    Toast.makeText(RegisterScreenActivity.this, "Invalid email", Toast.LENGTH_SHORT).show();
                } else if (newBirthday.length()<5) {
                    Toast.makeText(RegisterScreenActivity.this, "Enter birthday", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void formatDate(EditText editText, int year, int month, int day) {
        StringBuilder stringBuilder = new StringBuilder();
        editText.setText(stringBuilder.append(day).append("-").append(month + 1).append("-").append(year));
        stringBuilder.delete(0, stringBuilder.length());
    }

    private void setBirthday() {
        birthdayEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterScreenActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                formatDate(birthdayEditText, year, monthOfYear, dayOfMonth);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });
    }
}


