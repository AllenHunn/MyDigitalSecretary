package com.moonlite.mds;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

import java.util.ArrayList;

/**
 * Created by Pride on 3/4/14.
 */
public class SMSHandler {
    private static String SENT = "SMS_SENT";
    private static String DELIVERED = "SMS_DELIVERED";
    private static int MAX_SMS_MESSAGE_LENGTH = 160;

    public static void sendSMS(Context context, String phoneNumber, String message) {

        PendingIntent piSent = PendingIntent.getBroadcast(context, 0, new Intent(SENT), 0);
        PendingIntent piDelivered = PendingIntent.getBroadcast(context, 0, new Intent(DELIVERED), 0);
        SmsManager smsManager = SmsManager.getDefault();

        int length = message.length();
        if(length > MAX_SMS_MESSAGE_LENGTH) {
            ArrayList<String> messagelist = smsManager.divideMessage(message);
            smsManager.sendMultipartTextMessage(phoneNumber, null, messagelist, null, null);
        }
        else
            smsManager.sendTextMessage(phoneNumber, null, message, piSent, piDelivered);
    }
}
