package com.example.mobilprogramlamavize_202013709052;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;

public class RandomPage extends AppCompatActivity {

    //region Variables
    EditText adetTextArea, minTextArea, maxTextArea;
    String adetText, minText, maxText;
    LinearLayout scrollViewElements;
    // endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_page);
        SetLayoutVariables();
        ListenerForAdet();
        ListenerForMaksimum();
        ListenerForMinimum();
    }
    //region Functions
    private void Generate(){
        scrollViewElements.removeAllViews();
        Toast.makeText(getApplicationContext(), "Generated !", Toast.LENGTH_SHORT).show();
        int minValue = Integer.parseInt(minText);
        int maxValue = Integer.parseInt(maxText);
        for (int i = 0; Integer.parseInt(adetText) > i; i++) {
            int minLocal, maxLocal;

            while(true){
                minLocal = (int)Math.round((minValue + (Math.random() * (maxValue - minValue))));
                maxLocal = (int)Math.round((minValue + (Math.random() * (maxValue - minValue))));
                if((minLocal + 1) < maxLocal)
                    break;
            }

            int randomLocal = (int)Math.round((minLocal + (Math.random() * (maxLocal - minLocal))));
            int percentage = (int)(((double)(randomLocal - minLocal) / (maxLocal - minLocal)) * 100);


            View barInstance = getLayoutInflater().inflate(R.layout.activity_bar_instance, null);

            TextView minTextLocal = barInstance.findViewById(R.id.min);
            TextView maxTextLocal = barInstance.findViewById(R.id.max);
            TextView percentageTextLocal = barInstance.findViewById(R.id.percentage);
            ProgressBar progressBarLocal = barInstance.findViewById(R.id.progressBar);

            minTextLocal.setText(String.valueOf(minLocal));
            maxTextLocal.setText(String.valueOf(maxLocal));
            percentageTextLocal.setText(MessageFormat.format("RandomValue: {0} = {1}%",randomLocal,percentage));
            progressBarLocal.setProgress(percentage);

            scrollViewElements.addView(barInstance);
        }
    }
    private void SetLayoutVariables(){
        adetTextArea = findViewById(R.id.adetTextArea);
        minTextArea = findViewById(R.id.minimumTextArea);
        maxTextArea = findViewById(R.id.maksimumTextArea);
        scrollViewElements = findViewById(R.id.scrollViewElements);
    }
    //endregion

    //region Listeners
    private void ListenerForAdet(){
        adetTextArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    adetText = adetTextArea.getText().toString();
                    if(maxText != null && minText != null && adetText != null){
                        int minValue = Integer.parseInt(minText);
                        int maxValue = Integer.parseInt(maxText);
                        int adet = Integer.parseInt(adetText);
                        if((maxValue - minValue) >= 2 && adet > 0)
                            Generate();
                        else
                            Toast.makeText(getApplicationContext(), "Min ve Max değerleri arasındaki fark en az 2 olmalı.", Toast.LENGTH_SHORT).show();
                    }

                }
                catch(Exception e){

                }
            }
        });
    }
    private void ListenerForMaksimum(){
        minTextArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                try {
                    minText = minTextArea.getText().toString();
                    if(maxText != null && minText != null && adetText != null){
                        int minValue = Integer.parseInt(minText);
                        int maxValue = Integer.parseInt(maxText);
                        int adet = Integer.parseInt(adetText);
                        if((maxValue - minValue) >= 2 && adet > 0)
                            Generate();
                        else
                            Toast.makeText(getApplicationContext(), "Min ve Max değerleri arasındaki fark en az 2 olmalı.", Toast.LENGTH_SHORT).show();
                    }

                }
                catch(Exception e){

                }
            }
        });
    }
    private void ListenerForMinimum(){
        maxTextArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                try {
                    maxText = maxTextArea.getText().toString();
                    if(maxText != null && minText != null && adetText != null){
                        int minValue = Integer.parseInt(minText);
                        int maxValue = Integer.parseInt(maxText);
                        int adet = Integer.parseInt(adetText);
                        if((maxValue - minValue) >= 2 && adet > 0)
                            Generate();
                        else
                            Toast.makeText(getApplicationContext(), "Min ve Max değerleri arasındaki fark en az 2 olmalı.", Toast.LENGTH_SHORT).show();
                    }

                }
                catch(Exception e){

                }
            }
        });
    }
    //endregion
}