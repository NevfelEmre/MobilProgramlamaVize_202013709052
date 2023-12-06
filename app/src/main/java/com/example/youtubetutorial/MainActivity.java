package com.example.youtubetutorial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button converterButton, randomButton, smsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetLayoutVariables();
        ButtonFunctions();


    }

    private void ButtonFunctions(){
        converterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent converterPage = new Intent(MainActivity.this, ConverterPage.class);
                startActivity(converterPage);
            }
        });
        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent randomPage = new Intent(MainActivity.this, RandomPage.class);
                startActivity(randomPage);
            }
        });
        smsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsPage = new Intent(MainActivity.this, SmsPage.class);
                startActivity(smsPage);
            }
        });
    }


    private void SetLayoutVariables(){
        converterButton = findViewById(R.id.converterButton);
        randomButton = findViewById(R.id.randomButton);
        smsButton = findViewById(R.id.smsButton);
    }

}