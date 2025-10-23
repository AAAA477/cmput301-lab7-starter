package com.example.androiduitesting;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {
    public static final String EXTRA_CITY= "extra_city";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        TextView name = findViewById(R.id.text_city);
        Button back = findViewById(R.id.button_back);

        String cityName = getIntent().getStringExtra(EXTRA_CITY);
        if (cityName == null) cityName = "";
        name.setText(cityName);

        back.setOnClickListener(v -> finish());
    }
}