package com.example.callingapp;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ConfirmCallDialog {
    /**
     * Shows an Android (nicer) equivalent to JOptionPane
     *
     * @param strTitle Title of the Dialog box
     * @param strMsg   Message (body) of the Dialog box
     */
    public static void showInfoDialog (Context context, String strTitle, String strMsg, EditText phoneNumber)
    {
        // create the listener for the dialog
        final DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener ()
        {
            @Override
            public void onClick (DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        };

        // Create the AlertDialog.Builder object
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder (context);

        // Use the AlertDialog's Builder Class methods to set the title, icon, message, et al.
        // These could all be chained as one long statement, if desired
        alertDialogBuilder.setTitle (strTitle);
        alertDialogBuilder.setIcon (R.mipmap.ic_launcher);
        alertDialogBuilder.setMessage (strMsg);
        alertDialogBuilder.setCancelable (true);
        View view =(View) ((Activity) context).findViewById(R.id.main_activity);
        String yes = view.getResources().getText(R.string.no_dialog).toString();
        String no = view.getResources().getText(R.string.yes_dialog).toString();

        alertDialogBuilder.setPositiveButton (yes, listener);
        alertDialogBuilder.setNegativeButton (no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String callStr = phoneNumber.getText().toString();
                showCallingActivity(context, callStr);

            }
        });
        alertDialogBuilder.show();
    }

    private static void showCallingActivity(Context context, String callStr) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + callStr));
        context.startActivity(intent);
    }

    public static ArrayList<Integer> getNumberListFromJSONString (String gson){
        Type ArrayListIntegerType = new TypeToken<ArrayList<Integer>>(){}.getType();
        return new Gson().fromJson(gson, ArrayListIntegerType);
    }

    public static String getJSONStringFromNumberList (ArrayList<Integer> numberList){
        return new Gson().toJson(numberList);
    }



}