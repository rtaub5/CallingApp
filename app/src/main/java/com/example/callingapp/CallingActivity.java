package com.example.callingapp;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.callingapp.databinding.ActivityMainBinding;

public class CallingActivity  extends AppCompatActivity {


        private ActivityMainBinding binding;

        private TextView callingText;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(R.layout.calling_number);
            TextView callingText = findViewById(R.id.calling_textview);
        }

        private void setCallingText(String outputString)
        {
            callingText.setText(outputString);
        }




        //same thing as the ImageButton but swapped it for the FAB instead

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
