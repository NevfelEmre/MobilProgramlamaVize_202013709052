package com.example.youtubetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.MessageFormat;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class RandomPage extends AppCompatActivity {
    //region Variables
    EditText adetTextArea, minTextArea, maxTextArea;
    String adetText, minText, maxText;
    LinearLayout scrollViewElements;
    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_page);
        SetLayoutVariables();
        ListenerForAdet();
        ListenerForMin();
        ListenerForMax();
    }
    //region Listeners
    private void GenerateControl(){
        try{
            if(maxText != null && minText != null && adetText != null){
                int minValue = Integer.parseInt(minText);
                int maxValue = Integer.parseInt(maxText);
                int adet = Integer.parseInt(adetText);
                if((maxValue - minValue) >= 2 && adet > 0)
                    Generate();
                else
                    scrollViewElements.removeAllViews();
            }
        }catch(Exception e){}
    }
    private void ListenerForAdet(){
        adetTextArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                adetText = adetTextArea.getText().toString();
                GenerateControl();
            }
        });
    }
    private void ListenerForMin(){
        minTextArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                minText = minTextArea.getText().toString();
                GenerateControl();
            }
        });
    }
    private void ListenerForMax(){
        maxTextArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                maxText = maxTextArea.getText().toString();
                GenerateControl();
            }
        });
    }
    //endregion
    //region Functions
    private void Generate(){
        scrollViewElements.removeAllViews();
        Toast.makeText(getApplicationContext(),"Generated !", Toast.LENGTH_SHORT).show();
        int minValue = Integer.parseInt(minText);
        int maxValue = Integer.parseInt(maxText);
        for (int i = 0; Integer.parseInt(adetText) > i ; i++){
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
            TextView minTextLocal = barInstance.findViewById(R.id.minText);
            TextView maxTextLocal = barInstance.findViewById(R.id.maxText);
            TextView percentageTextLocal = barInstance.findViewById(R.id.randomText);
            ProgressBar progressBarLocal = barInstance.findViewById(R.id.progressBar);

            minTextLocal.setText(String.valueOf(minLocal));
            maxTextLocal.setText(String.valueOf(maxLocal));
            percentageTextLocal.setText(MessageFormat.format("Random value: {0} = {1}%",randomLocal,percentage));
            progressBarLocal.setProgress(percentage);

            scrollViewElements.addView(barInstance);
        }
    }
    private void SetLayoutVariables(){
        adetTextArea = findViewById(R.id.adetTextArea);
        minTextArea = findViewById(R.id.minTextArea);
        maxTextArea = findViewById(R.id.maxTextArea);
        scrollViewElements = findViewById(R.id.scrollViewElements);
    }
    //endregion
}