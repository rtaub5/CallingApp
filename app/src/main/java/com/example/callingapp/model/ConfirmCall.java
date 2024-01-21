package com.example.callingapp.model;

import com.google.gson.Gson;

public class ConfirmCall {

    private String phoneNumber;

    private Boolean isCallConfirmationEnabled;

    public ConfirmCall (String p)
    {
        phoneNumber = p;
        isCallConfirmationEnabled = true;
    }

    public ConfirmCall ()
    {
        phoneNumber = "";
        isCallConfirmationEnabled = true;
    }

    public ConfirmCall (String phoneNumber, boolean callConfirmationEnabled)
    {
        this.phoneNumber = phoneNumber;
        this.isCallConfirmationEnabled = callConfirmationEnabled;
    }

    public Boolean getCallConfirmationEnabled() {
        return isCallConfirmationEnabled;
    }

    public void setCallConfirmationEnabled(Boolean callConfirmationEnabled) {
        isCallConfirmationEnabled = callConfirmationEnabled;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



}
