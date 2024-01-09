package com.example.callingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


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

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;

    private EditText phoneNumber;

    FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        phoneNumber = findViewById(R.id.phoneNumber);
        ExtendedFloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   generateDialog.show(supportFragmentManager, "GAME_DIALOG");
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


    //same thing as the ImageButton but swapped it for the FAB instead
    private void handleFABClick()
    {
        //This is specifically for the phone part
        phoneNumber = findViewById(R.id.phoneNumber);
        String phone = phoneNumber.getText().toString();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("Tel: " + phone));
        startActivity(intent);
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
