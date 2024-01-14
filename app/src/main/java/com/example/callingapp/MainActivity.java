package com.example.callingapp;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.view.View;


import com.example.callingapp.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Integer> mNumberHistory;

    private final String mKey = "key";

    private ActivityMainBinding binding;

    private EditText phoneNumber;

    @Override
    protected void onStop(){
        super.onStop();
        saveOrDeleteInSharedPrefs();
    }

    private void saveOrDeleteInSharedPrefs(){
        SharedPreferences defaultSharedPreferences = getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = defaultSharedPreferences.edit();
        String num = phoneNumber.toString();
        editor.putString(mKey, num);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        phoneNumber = findViewById(R.id.phoneNumber);

        //if we are having a list of numbers dialed
        initializeHistoryList(savedInstanceState, mKey);

        ExtendedFloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phoneNumber.length() == 0) {
                    Snackbar.make(view, R.string.null_phone_num_message, Snackbar.LENGTH_SHORT).show();
                }
                else {

                    Utils.showInfoDialog(MainActivity.this, "Do you want to call this number?", phoneNumber.getText().toString(), phoneNumber);
                    System.out.println("hello");
                }
            }
        });
    }

    private void initializeHistoryList(Bundle savedInstanceState, String key){
        if(savedInstanceState != null){
            mNumberHistory = savedInstanceState.getIntegerArrayList(key);
        }
        else{
            String history = getDefaultSharedPreferences(this).getString(key, null);
            mNumberHistory = history == null ? new ArrayList<>(): Utils.getNumberListFromJSONString(history);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList(mKey, mNumberHistory);
    }

        @Override
        public boolean onCreateOptionsMenu (Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

    }
