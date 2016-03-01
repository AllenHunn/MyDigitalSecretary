package com.moonlite.mds;

/**
 * Created by Pride on 3/4/14.
 */
public class ContactInformation {

    public ContactInformation(){
        this(false, false, false);
    }

    public ContactInformation(boolean isContact, boolean isSpecialContact, boolean isMobileNumber){
        setContact(isContact);
        setSpecialContact(isSpecialContact);
        setMobileNumber(isMobileNumber);
    }

    private boolean isContact;
    private boolean isSpecialContact;
    private boolean isMobileNumber;

    public boolean isContact() {

        return isContact;
    }

    public void setContact(boolean isContact) {
        this.isContact = isContact;
    }

    public boolean isSpecialContact() {
        return isSpecialContact;
    }

    public void setSpecialContact(boolean isSpecialContact) {
        this.isSpecialContact = isSpecialContact;
    }

    public boolean isMobileNumber() {
        return isMobileNumber;
    }

    public void setMobileNumber(boolean isMobileNumber) {
        this.isMobileNumber = isMobileNumber;
    }
}
