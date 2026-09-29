package com.example.loginapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText mEmailInput;
    private EditText mPasswordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmailInput = findViewById(R.id.email_input);
        mPasswordInput = findViewById(R.id.password_input);
    }

    // Login Button နှိပ်လိုက်လျှင် အလုပ်လုပ်မည့် Function
    public void handleLogin(View view) {
        String email = mEmailInput.getText().toString();
        String password = mPasswordInput.getText().toString();

        // ကွက်လပ် မပြည့်ပါက သတိပေးရန်
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else {
            // Login အောင်မြင်ကြောင်း စာသားပြရန်
            Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
        }
    }
}