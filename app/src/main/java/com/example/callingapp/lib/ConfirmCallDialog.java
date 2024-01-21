package com.example.callingapp.lib;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.example.callingapp.R;
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
        final DialogInterface.OnClickListener listener = (dialog, which) -> dialog.dismiss();

        // Create the AlertDialog.Builder object
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder (context);

        // Use the AlertDialog's Builder Class methods to set the title, icon, message, et al.
        // These could all be chained as one long statement, if desired
        alertDialogBuilder.setTitle (strTitle);
        alertDialogBuilder.setIcon (R.mipmap.ic_launcher);
        alertDialogBuilder.setMessage (strMsg);
        alertDialogBuilder.setCancelable (true);
        String yes = context.getResources().getString(R.string.yes_dialog);
        String no = context.getResources().getString(R.string.no_dialog);

        alertDialogBuilder.setNegativeButton(yes, (dialog, id) -> handleYesClick(phoneNumber, context));
        alertDialogBuilder.setPositiveButton (no, listener);
        alertDialogBuilder.show();
    }

    private static void handleYesClick(EditText phoneNumber, Context context) {
        String callStr = phoneNumber.getText().toString();
        showCallingActivity(context, callStr);
    }

    public static void showCallingActivity(Context context, String callStr) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + callStr));
        context.startActivity(intent);
    }

}
