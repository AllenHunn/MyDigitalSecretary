package com.moonlite.mds;

import android.content.Context;
import android.content.SharedPreferences;
/**
 * Created by Pride on 3/4/14.
 */
public class Settings {
    public static final String PREFS_NAME = "MDSPrefs";

    private static Boolean sendAlertToNonContacts;
    private static Boolean sendAlertToAllContacts;
    private static Boolean sendAlertToMemberContacts;
    private static Boolean allowAllContactsThrough;
    private static Boolean allowSpecialContactsThrough;
    private static Boolean on;
    private static Boolean respondToCalls;

    private static String  codeWord;
    private static String  name;

    private static SharedPreferences.Editor getEditor(Context context){
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        return editor;
    }

    private static void persistValue(Context context, String valueName, boolean value){
        SharedPreferences.Editor editor = getEditor(context);
        editor.putBoolean(valueName, value);
        editor.apply();
    }

    private static void persistValue(Context context, String valueName, String value){
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(valueName, value);
        editor.apply();
    }

    public static boolean isRespondToCalls(Context context) {
        if (respondToCalls == null)
        {
            SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
            boolean temp = settings.getBoolean("respondToCalls", true);
            setRespondToCalls(context, temp);
        }
        return respondToCalls;
    }

    public static void setRespondToCalls(Context context, boolean respondToCalls) {
        Settings.respondToCalls = respondToCalls;
        persistValue(context, "respondToCalls", respondToCalls);
    }

    public static boolean isSendAlertToNonContacts(Context context) {
        if (sendAlertToNonContacts == null)
        {
            SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
            boolean temp = settings.getBoolean("sendAlertToNonContacts", false);
            setSendAlertToNonContacts(context, temp);
        }
        return sendAlertToNonContacts;
    }

    public static void setSendAlertToNonContacts(Context context, boolean sendAlertToNonContacts) {
        Settings.sendAlertToNonContacts = sendAlertToNonContacts;
        persistValue(context, "sendAlertToNonContacts", sendAlertToNonContacts);
    }

    public static boolean isSendAlertToAllContacts(Context context) {
        if (sendAlertToAllContacts == null)
        {
            SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
            boolean temp = settings.getBoolean("sendAlertToAllContacts", true);
            setSendAlertToAllContacts(context, temp);
        }
        return sendAlertToAllContacts;
    }

    public static void setSendAlertToAllContacts(Context context, boolean sendAlertToAllContacts) {
        Settings.sendAlertToAllContacts = sendAlertToAllContacts;
        persistValue(context, "sendAlertToAllContacts", sendAlertToAllContacts);
    }

    public static boolean isSendAlertToMemberContacts(Context context) {
        if (sendAlertToMemberContacts == null)
        {
            SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
            boolean temp = settings.getBoolean("sendAlertToMemberContacts", false);
            setSendAlertToMemberContacts(context, temp);
        }
        return sendAlertToMemberContacts;
    }

    public static void setSendAlertToMemberContacts(Context context, boolean sendAlertToMemberContacts) {
        Settings.sendAlertToMemberContacts = sendAlertToMemberContacts;
        persistValue(context, "sendAlertToMemberContacts", sendAlertToMemberContacts);
    }

    public static boolean isAllowAllContactsThrough(Context context) {
        if (allowAllContactsThrough == null)
        {
            SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
            boolean temp = settings.getBoolean("allowAllContactsThrough", false);
            setAllowAllContactsThrough(context, temp);
        }
        return allowAllContactsThrough;
    }

    public static void setAllowAllContactsThrough(Context context, boolean allowAllContactsThrough) {
        Settings.allowAllContactsThrough = allowAllContactsThrough;
        persistValue(context, "allowAllContactsThrough", allowAllContactsThrough);
    }

    public static boolean isAllowSpecialContactsThrough(Context context) {
        if (allowSpecialContactsThrough == null)
        {
            SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
            boolean temp = settings.getBoolean("allowSpecialContactsThrough", true);
            setAllowSpecialContactsThrough(context, temp);
        }
        return allowSpecialContactsThrough;
    }

    public static void setAllowSpecialContactsThrough(Context context, boolean allowSpecialContactsThrough) {
        Settings.allowSpecialContactsThrough = allowSpecialContactsThrough;
        persistValue(context, "allowSpecialContactsThrough", allowSpecialContactsThrough);
    }

    public static String getCodeWord(Context context) {
        if (codeWord == null)
        {
            SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
            String temp = settings.getString("codeWord", "codeword");
            setCodeWord(context, temp);
        }
        return codeWord;
    }

    public static void setCodeWord(Context context, String codeWord) {
        Settings.codeWord = codeWord;
        persistValue(context, "codeWord", codeWord);
    }

    public static String getName(Context context) {
        if (name == null)
        {
            SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
            String temp = settings.getString("name", "user");
            setName(context, temp);
        }
        return name;
    }

    public static void setName(Context context, String name) {
        Settings.name = name;
        persistValue(context, "name", name);
    }

    public static Boolean getOn(Context context) {
        if (on == null)
        {
            SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
            boolean temp = settings.getBoolean("on", true);
            setOn(context, temp);
        }
        return on;
    }

    public static void setOn(Context context, Boolean on) {
        Settings.on = on;
        persistValue(context, "on", on);
    }
}
