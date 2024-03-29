package com.example.callingapp.activities;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO;
import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.example.callingapp.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        attachFragment(savedInstanceState);
        setupActionBar();
    }

    private void attachFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
    }

    private void setupActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() !=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            setNightModePreferenceListener();
        }

        private void setNightModePreferenceListener() {
            Preference nightModePreference = findPreference(getString(R.string.night_mode_key));
            if (nightModePreference != null) {
                nightModePreference.setOnPreferenceChangeListener((preference, newValue) -> {
                    Boolean newBooleanValue = (Boolean) newValue;
                    setNightModeOnOrOff(newBooleanValue);
                    return true;
                });
            }
        }

        public static void setNightModeOnOffFromPreferenceValue(Context context, String keyNightMode) {
            setNightModeOnOrOff(isNightModePrefOn(context, keyNightMode));
        }

        public static void setNightModeOnOrOff(boolean setToOn) {
            int onMode  =  Build.VERSION.SDK_INT < 28 ? MODE_NIGHT_YES : MODE_NIGHT_FOLLOW_SYSTEM;
            AppCompatDelegate.setDefaultNightMode(setToOn ? onMode : MODE_NIGHT_NO);
        }

        private static boolean isNightModePrefOn(Context context, String keyNightMode) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            return defaultSharedPreferences.getBoolean(keyNightMode, true);
        }



    }

}
