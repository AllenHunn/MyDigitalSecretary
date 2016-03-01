package com.moonlite.mds;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.telephony.TelephonyManager;
import android.util.Log;

public class IncomingCallReceiver extends BroadcastReceiver {
    private static String SENT = "SMS_SENT";
    private static String DELIVERED = "SMS_DELIVERED";

    public IncomingCallReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!Settings.getOn(context)) return;

        try {
            TelephonyManager tmgr = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            switch (tmgr.getCallState()) {
                case TelephonyManager.CALL_STATE_RINGING:
                    AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                    switch (am.getRingerMode()) {
                        case AudioManager.RINGER_MODE_SILENT:
                            String incoming_number = intent.getStringExtra("incoming_number");
                            ContactInformation information = ContactHandler.getContactInformation(context, incoming_number);
                            if (!information.isContact())
                                return;
                            if ((Settings.isAllowAllContactsThrough(context) && information.isContact()) || (Settings.isAllowSpecialContactsThrough(context) && information.isSpecialContact())) {
                                AlertHandler.soundAlert(context, am, false);
                            } else if ((Settings.isSendAlertToMemberContacts(context) && information.isSpecialContact()) || (Settings.isSendAlertToAllContacts(context) && information.isContact())) {
                                if (Settings.isRespondToCalls(context) && information.isMobileNumber())
                                    sendAlert(context, incoming_number);
                            }
                            break;
                    }
                    break;
            }
        } catch (Exception e) {
            Log.e("Phone Receive Error", " " + e);
        }
    }

    private void sendAlert(Context context, String phoneNumber) {
        SMSHandler.sendSMS(context, phoneNumber, String.format(context.getResources().getString(R.string.sms_text), Settings.getName(context), Settings.getCodeWord(context)));
    }
}
