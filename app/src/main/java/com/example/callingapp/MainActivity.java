package com.example.callingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;


import com.example.callingapp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;

    private EditText phoneNumber;
    private ImageButton callButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //This is specifically for the phone part
        phoneNumber = findViewById(R.id.phoneNumber);
        callButton = findViewById(R.id.callButton);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneNumber.getText().toString();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("Tel: " + phone));
                startActivity(intent);
            }
        });
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
