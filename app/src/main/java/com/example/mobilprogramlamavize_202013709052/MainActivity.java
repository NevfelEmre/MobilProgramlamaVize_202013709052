package com.example.mobilprogramlamavize_202013709052;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //region variables
    TextView txtNo, txtIsim;
    Button smsPageButton, converterPageButton, randomPageButton;
    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetLayoutVariables();
        ButtonFunctions();
    }
    private void ButtonFunctions(){
        smsPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent smsPage = new Intent(MainActivity.this, SmsPage.class);
                startActivity(smsPage);
            }
        });
        converterPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent converterPage = new Intent(MainActivity.this, ConverterPage.class);
                startActivity(converterPage);
            }
        });
        randomPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent randomPage = new Intent(MainActivity.this, RandomPage.class);
                startActivity(randomPage);
            }
        });
    }
    private void SetLayoutVariables(){
        txtNo=findViewById(R.id.txt_ogrNo);
        txtIsim=findViewById(R.id.txt_Isim);
        smsPageButton=findViewById(R.id.smsPageButton);
        converterPageButton=findViewById(R.id.converterPageButton);
        randomPageButton=findViewById(R.id.randomPageButton);
    }
}