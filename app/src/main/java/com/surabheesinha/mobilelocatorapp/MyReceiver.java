package com.surabheesinha.mobilelocatorapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by surabheesinha on 9/3/17.
 */

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final SmsManager sms = SmsManager.getDefault();

            if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED"))
            {
                // Retrieves a map of extended data from the intent.
                final Bundle bundle = intent.getExtras();


                SmsMessage[] msgs = null;
                String str = "";

                try
                {
                    if (bundle != null)
                    {
                        //---retrieve the SMS message received---
                        Object[] pdus = (Object[]) bundle.get("pdus");

                        msgs = new SmsMessage[pdus.length];
                        for (int i = 0; i < msgs.length; i++)
                        //for(Object obj : msgs)
                        {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                String format = bundle.getString("format");
                                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                            }
                            else {
                                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                            }


                            str += "SMS from " + msgs[i].getOriginatingAddress();
                            str += " :";
                            str += msgs[i].getMessageBody().toString();
                            str += "\n";
                        }
                        /*for(Object aobject:pdus)
                        {
                            msgs = getIncomingMessage(aobject, bundle);
                            str+="SMS from "+ msgs.getOriginatingAddress();
                            str+=":";
                            str += msgs.getMessageBody().toString();
                            str += "\n";
                        }*/


                        String replyPhone = msgs[0].getOriginatingAddress();
                        String request = msgs[0].getMessageBody().toString();

                        if(request.equals("RING"))
                        {
                            //Toast.makeText(context,"testing1",Toast.LENGTH_SHORT).show();

                            //             this.abortBroadcast();
                            Intent i = new Intent(context, RingActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            i.putExtra("number", replyPhone);
                            i.putExtra("message", request);
                            context.startActivity(i);
                        }
                    }
                }
                catch (Exception e)
                {
                    Log.e("MyReceiver", "Exception smsReceiver" +e);

                }
            }//close if

    }//closeonreceive()
}
