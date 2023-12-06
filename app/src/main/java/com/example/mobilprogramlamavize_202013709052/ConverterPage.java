package com.example.mobilprogramlamavize_202013709052;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

public class ConverterPage extends AppCompatActivity{
    //region Variables
    EditText decimalTextArea, megaByteTextArea, celciusTextArea;
    TextView decimalResult, megaByteResult, celciusResult;
    String decimalText, megaByteText, celciusText;
    String decimalSpinnerSelectedOption, megaByteSpinnerSelectedOption, celciusSpinnerSelectedOption;
    // endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter_page);
        SetLayoutVariables();
        SetSpinner1();
        SetSpinner2();
        SetSpinner3();
        ListenerForDecimal();
        ListenerForMegabyte();
        ListenerForCelcius();

    }

    //region Functions
    private void SetLayoutVariables(){
        decimalTextArea = findViewById(R.id.decimalTextArea);
        decimalResult = findViewById(R.id.decimalResult);
        megaByteTextArea = findViewById(R.id.megaByteTextArea);
        megaByteResult = findViewById(R.id.megaByteResult);
        celciusTextArea = findViewById(R.id.celciusTextArea);
        celciusResult = findViewById(R.id.celciusResult);
    }
    private String DecimalToBinary(long value){
        return Long.toBinaryString(value);
    }
    private String DecimalToOctal(long value){
        return Long.toOctalString(value);
    }
    private String DecimalToHexadecimal(long value){
        return Long.toHexString(value);
    }
    /////////////////////////////////////////////////////////////////////////
    private String MegabyteToKilobyte(long value){
        return Long.toString(value * 1024);
    }
    private String MegabyteToByte(long value){
        return Long.toString(value * 1024 * 1024);
    }
    private String MegabyteToKibibyte(long value){
        return Double.toString(value *  (Math.pow(10,6) / Math.pow(2,10)));
    }
    private String MegabyteToBit(long value){
        return Long.toString(value * 1024 * 1024 * 8);
    }
    /////////////////////////////////////////////////////////////////////////
    private String CelciusToFahrenheit(double value){
        return Double.toString((value * (9/5))+32);
    }
    private String CelciusToKelvin(double value){
        if((value + 273.15) <= 0)
            return "Mutlak Sıfır !";
        return Double.toString(value + 273.15);
    }
    //endregion
    //region TextListeners
    private void ListenerForDecimal(){
        decimalTextArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    decimalText = decimalTextArea.getText().toString();
                    if(decimalText != null){
                        switch(decimalSpinnerSelectedOption){
                            case "Binary (2)":
                                decimalResult.setText(DecimalToBinary(Long.parseLong(decimalText)));
                                break;
                            case "Octal (8)":
                                decimalResult.setText(DecimalToOctal(Long.parseLong(decimalText)));
                                break;
                            case "Hexadecimal (16)":
                                decimalResult.setText(DecimalToHexadecimal(Long.parseLong(decimalText)));
                                break;
                        }
                    }
                }
                catch(Exception e){
                    decimalResult.setText("");
                }
            }
        });
    }
    private void ListenerForMegabyte(){
        megaByteTextArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                try {
                    megaByteText = megaByteTextArea.getText().toString();
                    if(megaByteText != null){
                        switch(megaByteSpinnerSelectedOption){
                            case "Kilobyte":
                                megaByteResult.setText(MegabyteToKilobyte(Long.parseLong(megaByteText)));
                                break;
                            case "Byte":
                                megaByteResult.setText(MegabyteToByte(Long.parseLong(megaByteText)));
                                break;
                            case "Kibibyte":
                                megaByteResult.setText(MegabyteToKibibyte(Long.parseLong(megaByteText)));
                                break;
                            case "Bit":
                                megaByteResult.setText(MegabyteToBit(Long.parseLong(megaByteText)));
                                break;
                        }
                    }
                }
                catch(Exception e){
                    megaByteResult.setText("");
                }
            }
        });
    }
    private void ListenerForCelcius(){
        celciusTextArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                try {
                    celciusText = celciusTextArea.getText().toString();
                    if(celciusText != null){
                        switch(celciusSpinnerSelectedOption){
                            case "Fahrenheit":
                                celciusResult.setText(CelciusToFahrenheit(Double.parseDouble(celciusText)));
                                break;
                            case "Kelvin":
                                celciusResult.setText(CelciusToKelvin(Double.parseDouble(celciusText)));
                                break;
                        }
                    }
                }
                catch(Exception e){
                    celciusResult.setText("");
                }
            }
        });
    }
    //endregion
    //region Spinners
    private void SetSpinner1(){
        Spinner spinner1 = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.decimalSpinner, android.R.layout.simple_spinner_item);
        adapter1 .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    decimalSpinnerSelectedOption = parent.getItemAtPosition(position).toString();
                    if(decimalText != null){
                        switch(decimalSpinnerSelectedOption){
                            case "Binary (2)":
                                decimalResult.setText(DecimalToBinary(Long.parseLong(decimalText)));
                                break;
                            case "Octal (8)":
                                decimalResult.setText(DecimalToOctal(Long.parseLong(decimalText)));
                                break;
                            case "Hexadecimal (16)":
                                decimalResult.setText(DecimalToHexadecimal(Long.parseLong(decimalText)));
                                break;
                        }
                    }
                }
                catch(Exception e){
                    decimalResult.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
    private void SetSpinner2(){
        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.megaByteSpinner, android.R.layout.simple_spinner_item);
        adapter2 .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                megaByteSpinnerSelectedOption = parent.getItemAtPosition(position).toString();
                try {
                    megaByteText = megaByteTextArea.getText().toString();
                    if(megaByteText != null){
                        switch(megaByteSpinnerSelectedOption){
                            case "Kilobyte":
                                megaByteResult.setText(MegabyteToKilobyte(Long.parseLong(megaByteText)));
                                break;
                            case "Byte":
                                megaByteResult.setText(MegabyteToByte(Long.parseLong(megaByteText)));
                                break;
                            case "Kibibyte":
                                megaByteResult.setText(MegabyteToKibibyte(Long.parseLong(megaByteText)));
                                break;
                            case "Bit":
                                megaByteResult.setText(MegabyteToBit(Long.parseLong(megaByteText)));
                                break;
                        }
                    }
                }
                catch(Exception e){
                    megaByteResult.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
    private void SetSpinner3(){
        Spinner spinner3 = findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.celciusSpinner, android.R.layout.simple_spinner_item);
        adapter3 .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                celciusSpinnerSelectedOption = parent.getItemAtPosition(position).toString();
                try {
                    celciusText = celciusTextArea.getText().toString();
                    if(celciusText != null){
                        switch(celciusSpinnerSelectedOption){
                            case "Fahrenheit":
                                celciusResult.setText(CelciusToFahrenheit(Double.parseDouble(celciusText)));
                                break;
                            case "Kelvin":
                                celciusResult.setText(CelciusToKelvin(Double.parseDouble(celciusText)));
                                break;
                        }
                    }
                }
                catch(Exception e){
                    celciusResult.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
    //endregion
}