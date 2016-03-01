package com.moonlite.mds;

import android.content.Context;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

/**
 * Created by Pride on 3/4/14.
 */
public class AlertHandler {
    public static void soundAlert(Context context, AudioManager am, boolean soundAlert){
        am.setRingerMode(am.RINGER_MODE_NORMAL);
        am.setStreamVolume(am.STREAM_RING, am.getStreamMaxVolume(am.STREAM_RING), 0);
        if (!soundAlert) return;
        Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        Ringtone r = RingtoneManager.getRingtone(context, alert);
        r.play();
    }
}
