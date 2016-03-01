package com.moonlite.mds;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {

    // ---sends an SMS message to another device---

    public SMSReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!Settings.getOn(context)) return;

        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {
                    AudioManager am = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
                    switch (am.getRingerMode()) {
                        case AudioManager.RINGER_MODE_SILENT:
                            SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                            String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                            String message = currentMessage.getDisplayMessageBody();
                            if (message.toLowerCase().equals(Settings.getCodeWord(context).toLowerCase())){
                                try {
                                    AlertHandler.soundAlert(context, am, true);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e("MDS", e.getMessage());
                                }
                            }
                            else if (message.contains("- MDS")) { return;}
                            else
                            {
                                if (Settings.isSendAlertToNonContacts(context))
                                {
                                    sendAlert(context, phoneNumber);
                                }
                                else
                                {
                                    ContactInformation information = ContactHandler.getContactInformation(context, phoneNumber);
                                    if ((Settings.isSendAlertToAllContacts(context) && information.isContact()) || (Settings.isSendAlertToMemberContacts(context) && information.isSpecialContact())){
                                        //if (!Settings.isRespondToCalls(context) || information.isMobileNumber())
                                        //{
                                            sendAlert(context, phoneNumber);
                                        //}
                                    }
                                }
                            }
                            break;
                        case AudioManager.RINGER_MODE_VIBRATE:
                            break;
                        case AudioManager.RINGER_MODE_NORMAL:
                            break;
                    }
                }
            }
        } catch (Exception e) {
            Log.e("MDS", "Exception smsReceiver" + e);
        }
    }

    private void sendAlert(Context context, String phoneNumber) {
        SMSHandler.sendSMS(context, phoneNumber, String.format(String.format(context.getResources().getString(R.string.sms_text), Settings.getName(context), Settings.getCodeWord(context))));
    }
}
