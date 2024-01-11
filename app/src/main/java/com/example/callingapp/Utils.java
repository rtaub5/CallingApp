package com.example.callingapp;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {
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
               // TextView outputText = ((Activity) context).findViewById(R.id.calling_textview);
              //  outputText.setText("Hello");

            }
        });
        alertDialogBuilder.show();
    }

    private static void showCallingActivity(Context context, String callStr) {
       // Intent intent = new Intent(context.getApplicationContext(), CallingActivity.class);
       // context.startActivity(intent);
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + callStr));  // replace just the "321..." with your TextView's text...
        context.startActivity(intent);

    }



}
