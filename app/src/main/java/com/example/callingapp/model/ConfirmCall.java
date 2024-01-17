package com.example.callingapp.model;

import com.google.gson.Gson;

public class ConfirmCall {

    private String phoneNumber;

    private Boolean isCallConfirmationEnabled;

    public ConfirmCall (String p)
    {
        phoneNumber = p;
        isCallConfirmationEnabled = false;
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


    /**
     * Reverses the game object's serialization as a String
     * back to a ConfirmCall object
     *
     * @param json The serialized String of the game object
     * @return The game object
     */
    public static ConfirmCall getGameFromJSON (String json)
    {
        Gson gson = new Gson ();
        return gson.fromJson (json, ConfirmCall.class);
    }

    public static String getJSONFromGame (ConfirmCall obj)
    {
        Gson gson = new Gson ();
        return gson.toJson (obj);
    }

    public String getJSONFromCurrentGame()
    {
        return getJSONFromGame(this);
    }
}
