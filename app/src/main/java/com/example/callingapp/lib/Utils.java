package com.example.callingapp.lib;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.example.callingapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {


    public static void showInfoDialog (Context context, String strTitle, String strMsg)
    {
        // create the listener for the dialog
        final DialogInterface.OnClickListener listener = (dialog, which) -> dialog.dismiss();

        // Create the AlertDialog.Builder object
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder (context);

        // Use the AlertDialog's Builder Class methods to set the title, icon, message, et al.
        // These could all be chained as one long statement, if desired
        alertDialogBuilder.setTitle (strTitle);
        alertDialogBuilder.setIcon (R.mipmap.ic_launcher);
        alertDialogBuilder.setMessage (strMsg);
        alertDialogBuilder.setCancelable (true);
        alertDialogBuilder.setNeutralButton (context.getString (android.R.string.ok), listener);

        // Create and Show the Dialog
        alertDialogBuilder.show();
    }

}
