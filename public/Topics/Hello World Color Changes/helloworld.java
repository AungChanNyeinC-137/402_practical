package com.example.hellocompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mHelloTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelloTextView = findViewById(R.id.hello_textview);
    }

    // Button နှိပ်ရင် အရောင်ကျပန်း (Random) ပြောင်းပေးမည့် Function
    public void changeColor(View view) {
        Random random = new Random();
        
        // Random RGB အရောင်ထုတ်ယူခြင်း
        int color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        
        mHelloTextView.setTextColor(color);
    }
}