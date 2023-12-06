package com.example.mobilprogramlamavize_202013709052;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.Manifest;

public class SmsPage extends AppCompatActivity {
    //region Variables
    EditText phoneNumberTextArea, messageTextArea;
    String phoneNumberText, messageText;
    Button sendButton;
    private static final int PERMISSIONS_REQUEST_SEND_SMS = 123;
    // endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSIONS_REQUEST_SEND_SMS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_page);
        SetLayoutVariables();
        phoneNumberText = phoneNumberTextArea.getText().toString();
        messageText = messageTextArea.getText().toString();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumberText = phoneNumberTextArea.getText().toString();
                messageText = messageTextArea.getText().toString();
                SendMessage();
            }
        });

    }
    //region Functions
    private void SetLayoutVariables(){
        phoneNumberTextArea = findViewById(R.id.phoneNumberTextArea);
        messageTextArea = findViewById(R.id.messageTextArea);
        sendButton = findViewById(R.id.sendButton);
    }
    private void SendMessage(){

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumberText, null, messageText, null, null);
            Toast.makeText(getApplicationContext(), "Message successfully sent!", Toast.LENGTH_SHORT).show();
        } catch (IllegalArgumentException e) {
            Toast.makeText(getApplicationContext(), "Invalid destination number!", Toast.LENGTH_SHORT).show();
        } catch (SecurityException e) {
            Toast.makeText(getApplicationContext(), "Permission denied to send SMS!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Failed to send message!", Toast.LENGTH_SHORT).show();
        }
    }
    //endregion
}