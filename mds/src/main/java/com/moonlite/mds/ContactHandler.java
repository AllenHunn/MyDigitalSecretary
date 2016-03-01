package com.moonlite.mds;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

/**
 * Created by Pride on 3/4/14.
 */
public class ContactHandler {

    public static ContactInformation getContactInformation(Context context, String phoneNumber){
        return getContact(context, phoneNumber);
    }

    private static ContactInformation getContact(Context context, String phoneNumber) {
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
        Cursor cursor = cr.query(uri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup.TYPE, ContactsContract.PhoneLookup.STARRED}, null, null, null);
        if (cursor == null) {
            return new ContactInformation();
        }
        ContactInformation ci = new ContactInformation();
        if(cursor.moveToFirst()) {
            String contactName = null;
            boolean starred = false;
            int phoneType;
            boolean isMobile;

            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
            starred = cursor.getInt(cursor.getColumnIndex(ContactsContract.PhoneLookup.STARRED)) == 1;
            phoneType = cursor.getInt(cursor.getColumnIndex(ContactsContract.PhoneLookup.TYPE));
            isMobile = (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE || phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_WORK_MOBILE);

           ci = new ContactInformation(true, starred, isMobile);
        }

        if(cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return ci;
    }
}
