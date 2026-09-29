package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private EditText share_edittext, website_edittext, location_edittext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        share_edittext = findViewById(R.id.share_edittext);
        website_edittext = findViewById(R.id.website_edittext);
        share_edittext = findViewById(R.id.share_edittext);
    }
}