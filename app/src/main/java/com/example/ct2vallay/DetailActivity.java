package com.example.ct2vallay;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView bodyTextView = findViewById(R.id.bodyTextView);

        // Получение данных из Intent
        String title = getIntent().getStringExtra("title");
        String body = getIntent().getStringExtra("body");

        // Установка данных в TextView
        titleTextView.setText(title);
        bodyTextView.setText(body);
    }
}
