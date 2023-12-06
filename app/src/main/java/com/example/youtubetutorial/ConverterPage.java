package com.example.youtubetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ConverterPage extends AppCompatActivity {
    //region LayoutVariables
    EditText decimalTextArea, megaByteTextArea, celciusTextArea;
    TextView decimalResult, megaByteResult, celciusResult;
    Spinner decimalSpinner, megaByteSpinner, celciusSpinner;
    String decimalText, megaByteText, celciusText;
    String decimalSpinnerText, megaByteSpinnerText, celciusSpinnerText;
    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter_page);
        SetVariables();
        SetDecimalSpinner();
        SetMegabyteSpinner();
        SetCelciusSpinner();
        ListenerForDecimal();
        ListenerForMegabyte();
        ListenerForCelcius();

    }
    //region TextListeners
    private void ListenerForDecimal(){
        decimalTextArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    decimalText = decimalTextArea.getText().toString();
                    switch(decimalSpinnerText){
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
                }catch(Exception e){
                    decimalResult.setText("");
                }
            }
        });
    }
    private void ListenerForMegabyte(){
        megaByteTextArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    megaByteText = megaByteTextArea.getText().toString();
                    switch(megaByteSpinnerText){
                        case "Kilobyte":
                            megaByteResult.setText(MegabyteToKilobyte(Long.parseLong(megaByteText)));
                            break;
                        case "Byte":
                            megaByteResult.setText(MegabyteToByte(Long.parseLong(megaByteText)));
                            break;
                        case "Kibibyte":
                            megaByteResult.setText(MegabyteToKibiyte(Long.parseLong(megaByteText)));
                            break;
                        case "Bit":
                            megaByteResult.setText(MegabyteToBit(Long.parseLong(megaByteText)));
                            break;
                    }
                }catch(Exception e){
                    megaByteResult.setText("");
                }
            }
        });
    }
    private void ListenerForCelcius(){
        celciusTextArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    celciusText = celciusTextArea.getText().toString();
                    switch(celciusSpinnerText){
                        case "Fahrenheit":
                            celciusResult.setText(CelciusToFahrenheit(Double.parseDouble(celciusText)));
                            break;
                        case "Kelvin":
                            celciusResult.setText(CelciusToKelvin(Double.parseDouble(celciusText)));
                            break;
                    }
                }catch(Exception e){
                    celciusResult.setText("");
                }
            }
        });
    }
    //endregion
    //region ConvertMethods
    private String DecimalToBinary(long value){
        return Long.toBinaryString(value);
    }
    private String DecimalToOctal(long value){
        return Long.toOctalString(value);
    }
    private String DecimalToHexadecimal(long value){
        return Long.toHexString(value);
    }
    //////////////////////////////////////////////////////////////////////////////////
    private String MegabyteToKilobyte(long value){
        return String.valueOf(value * 1024);
    }
    private String MegabyteToByte(long value){
        return String.valueOf(value * 1024 * 1024);
    }
    private String MegabyteToKibiyte(long value){
        return String.valueOf(value * Math.pow(10,6) / Math.pow(2,10));
    }
    private String MegabyteToBit(long value){
        return String.valueOf(value * 1024 * 1024 * 8);
    }
    //////////////////////////////////////////////////////////////////////////////////
    private String CelciusToFahrenheit(double value){
        return Double.toString((value * (9/5)) + 32);
    }
    private String CelciusToKelvin(double value){
        if((value + 273.15) <= 0)
            return "Mutlak Sıfır !";
        return Double.toString(value + 273.15);
    }
    //endregion
    //region Spinners
    private void SetDecimalSpinner(){
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.decimalSpinnerValues, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        decimalSpinner.setAdapter(adapter1);
        decimalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try{
                    decimalSpinnerText = parent.getItemAtPosition(position).toString();
                    switch(decimalSpinnerText){
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
                }catch(Exception e){
                    decimalResult.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }
    private void SetMegabyteSpinner(){
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.megaByteSpinnerValues, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        megaByteSpinner.setAdapter(adapter2);
        megaByteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try{
                    megaByteSpinnerText = parent.getItemAtPosition(position).toString();
                    switch(megaByteSpinnerText){
                        case "Kilobyte":
                            megaByteResult.setText(MegabyteToKilobyte(Long.parseLong(megaByteText)));
                            break;
                        case "Byte":
                            megaByteResult.setText(MegabyteToByte(Long.parseLong(megaByteText)));
                            break;
                        case "Kibibyte":
                            megaByteResult.setText(MegabyteToKibiyte(Long.parseLong(megaByteText)));
                            break;
                        case "Bit":
                            megaByteResult.setText(MegabyteToBit(Long.parseLong(megaByteText)));
                            break;
                    }
                }catch(Exception e){
                    megaByteResult.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }
    private void SetCelciusSpinner(){
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.celciusSpinnerValues, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        celciusSpinner.setAdapter(adapter3);
        celciusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try{
                    celciusSpinnerText = parent.getItemAtPosition(position).toString();
                    switch(celciusSpinnerText){
                        case "Fahrenheit":
                            celciusResult.setText(CelciusToFahrenheit(Double.parseDouble(celciusText)));
                            break;
                        case "Kelvin":
                            celciusResult.setText(CelciusToKelvin(Double.parseDouble(celciusText)));
                            break;
                    }
                }catch(Exception e){
                    celciusResult.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }
    //endregion
    private void SetVariables(){
        decimalTextArea = findViewById(R.id.decimalTextArea);
        megaByteTextArea = findViewById(R.id.megaByteTextArea);
        celciusTextArea = findViewById(R.id.celciusTextArea);
        decimalResult = findViewById(R.id.decimalResult);
        megaByteResult = findViewById(R.id.megaByteResult);
        celciusResult = findViewById(R.id.celciusResult);
        decimalSpinner = findViewById(R.id.decimalSpinner);
        megaByteSpinner = findViewById(R.id.megaByteSpinner);
        celciusSpinner = findViewById(R.id.celciusSpinner);
    }
}