package com.example.callingapp.activities;

import static androidx.preference.PreferenceManager.getDefaultSharedPreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;


import com.example.callingapp.R;
import com.example.callingapp.databinding.ActivityMainBinding;
import com.example.callingapp.model.ConfirmCall;
import com.example.callingapp.lib.ConfirmCallDialog;
import com.example.callingapp.lib.Utils;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;



public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final String mKey = "key";
    private ConfirmCall confirmCall;
    private boolean isConfirmCallOn;
    private String mKeyIsConfirmCallOn;

    private EditText phoneNumber;


    @Override
    protected void onStop() {
        super.onStop();
        saveOrDeleteInSharedPrefs();
    }

    private void saveOrDeleteInSharedPrefs() {
        SharedPreferences defaultSharedPreferences = getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = defaultSharedPreferences.edit();
        String num = phoneNumber.toString();
        editor.putString(mKey, num);
        editor.putBoolean(mKeyIsConfirmCallOn, isConfirmCallOn);
        editor.apply();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        phoneNumber = findViewById(R.id.phoneNumber);
        mKeyIsConfirmCallOn = getString(R.string.confirm_call_key);

        confirmCall = new ConfirmCall();
        restoreFromPreferencesConfirmCallStatus();

        ExtendedFloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> handleFABClick(view));
    }

    private void handleFABClick(View view) {
        if (phoneNumber.length() == 0) {
            Snackbar.make(view, R.string.null_phone_num_message, Snackbar.LENGTH_SHORT).show();
        } else {
            confirmCall.setPhoneNumber(phoneNumber.getText().toString());
            isConfirmCallOn = confirmCall.getCallConfirmationEnabled();
            if (isConfirmCallOn) {
                ConfirmCallDialog.showInfoDialog(MainActivity.this, getString(R.string.confirm_call_dialog_title), phoneNumber.getText().toString(), phoneNumber);
            } else {
                ConfirmCallDialog.showCallingActivity(MainActivity.this, phoneNumber.getText().toString());
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            showSettings();
            return true;
        }
        if (id == R.id.about) {
            View view = findViewById(R.id.main_activity);
            Utils.showInfoDialog(this, view.getResources().getText(R.string.about_title).toString(), view.getResources().getText(R.string.about_text).toString());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void showSettings() {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        settingsLauncher.launch(intent);
        restoreFromPreferencesConfirmCallStatus();
    }

    ActivityResultLauncher<Intent> settingsLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> restoreFromPreferencesConfirmCallStatus());


    private void restoreFromPreferencesConfirmCallStatus() {
        SharedPreferences sp = getDefaultSharedPreferences(this);
        isConfirmCallOn = sp.getBoolean(mKeyIsConfirmCallOn, true);
        confirmCall.setCallConfirmationEnabled(isConfirmCallOn);
    }


}
