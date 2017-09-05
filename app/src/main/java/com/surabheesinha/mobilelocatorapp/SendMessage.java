package com.surabheesinha.mobilelocatorapp;

import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;

/**
 * Created by surabheesinha on 9/3/17.
 */

public class SendMessage extends AppCompatActivity {
    EditText ed1,ed2;
    String SMS_Sent="Message sent";
    Intent intentSent;
    PendingIntent piSent;
    BroadcastReceiver brSent;
    IntentFilter infSent;

    String SMS_Del="Message delivered";
    Intent intentDel;
    PendingIntent piDel;
    BroadcastReceiver brDel;
    IntentFilter infDel;
    Button sendButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        ed1 = (EditText)findViewById(R.id.editText1);
        ed2 = (EditText)findViewById(R.id.editText2);
        sendButton= (Button)findViewById(R.id.send);


        sendButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},PERMISSION_SEND_SMS);
                String number = ed1.getText().toString();
                String message = ed2.getText().toString();

                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(number, null, message, piSent, piDel);
                intentSent.putExtra("message",message);
                intentSent.putExtra("number",number);

            }
        });


        intentSent = new Intent(SMS_Sent);
        piSent = PendingIntent.getBroadcast(getApplicationContext(), 0, intentSent, 0);
        brSent = new BroadcastReceiver(){

            @Override
            public void onReceive(Context arg0, Intent arg1) {

                switch(getResultCode())
                {

                    case RESULT_OK:
                        Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_LONG).show();

                        break;

                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getApplicationContext(),"Failure", Toast.LENGTH_LONG);
                        break;

                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getApplicationContext(), "No Service ", Toast.LENGTH_LONG).show();
                        break;

                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getApplicationContext(),"Radio off",Toast.LENGTH_LONG).show();
                        break;

                    default:
                        break;


                }

            }

        };
        infSent = new IntentFilter(SMS_Sent);
        intentSent = new Intent(SMS_Sent);

        piSent = PendingIntent.getBroadcast(getApplicationContext(), 0, intentSent, 0);
        brSent = new BroadcastReceiver(){

            @Override
            public void onReceive(Context context, Intent intent) {
                switch(getResultCode())
                {

                    case RESULT_OK:
                        Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_LONG).show();

                        break;

                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getApplicationContext(),"Failure", Toast.LENGTH_LONG);
                        break;

                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getApplicationContext(), "No Service ", Toast.LENGTH_LONG).show();
                        break;

                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getApplicationContext(),"Radio off",Toast.LENGTH_LONG).show();
                        break;

                    default:
                        break;


                }
            }
        };

        infDel = new IntentFilter(SMS_Del);


    }




    @Override
    protected void onPause() {

        super.onPause();
        registerReceiver(brSent, infSent);
        registerReceiver(brDel, infDel);
        unregisterReceiver(brSent);
        unregisterReceiver(brDel);

    }


    @Override
    protected void onResume() {

        super.onResume();

        registerReceiver(brSent, infSent);
        registerReceiver(brDel, infDel);

        //unregisterReceiver(brSent);
        //unregisterReceiver(brDel);

    }



    /*public void send(View v){

        String number = ed1.getText().toString();
        String message = ed2.getText().toString();

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(number, null, message, piSent, piDel);
        intentSent.putExtra("message",message);



    }*/


}
