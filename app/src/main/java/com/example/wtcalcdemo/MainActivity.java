package com.example.wtcalcdemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txtViewResults;

    //Added another line from local on Feb 8

    //Added another line from remote at 10:33am

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.mipmap.ic_launcher_wt_round);
        actionBar.setTitle(R.string.txtTitle);

        txtViewResults = findViewById(R.id.txtViewResults);
        EditText editTextInputWt = findViewById(R.id.editTextInputWt);
        RadioGroup radGroupConv = findViewById(R.id.radGroupConv);
        Button btnConvertWt = findViewById(R.id.btnConvertWt);
        radGroupConv.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radBtnLbsToKgs){
                    Toast.makeText(MainActivity.this,
                            "Let us convert Pounds to Kilos",
                            Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.radBtnKgsToLbs){
                    Toast.makeText(MainActivity.this,
                            "Let us convert kilos to pounds",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

       // radGroupConv.getCheckedRadioButtonId() --> -1 if nothing is onContextItemSelected()
        // --> R.id.radBtnLbsToKgs if that radio button is checked
        // --> R.id.radBtnKgsToLbs if the other radio button is checked

        //First, set up a button click listener for btnConvertWt
        //In that listener, do the following:
        //Display an error message if no radio button is checked (id is -1)
        //Display an error message if editTextInputWt is empty
        //If Pounds to Kilos is checked and parsed inputWt is > 1000, display an error message saying input weight in pounds must be <= 1000
        //Otherwise compute outputWtInKilos = inputWt/2.2
        //If Kilos to Pounds is checked and parsed inputWt is > 500, display an error message saying input weight in pounds must be <= 500
        //Otherwise compute outputWtInPounds = inputWt*2.2
        //Display the output weight in txtViewResults using correct
        //unit as Kgs (if converting from pounds to kilos) and
        //Lbs (if converting kilos to pounds)

        btnConvertWt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radGroupConv.getCheckedRadioButtonId() == -1){
                    Toast.makeText(MainActivity.this, "Please check conversion type",
                            Toast.LENGTH_SHORT).show();
                } else {
                    double inputWt = 0, outputWt = 0;
                    try {
                        inputWt =
                                Double.parseDouble(editTextInputWt
                                        .getText().toString());
                        if (inputWt <= 0) {
                            Toast.makeText(MainActivity.this, "Input Weight must be > 0",
                                    Toast.LENGTH_SHORT).show();
                        } else if (radGroupConv.getCheckedRadioButtonId()
                                == R.id.radBtnKgsToLbs){
                            if (inputWt > 500){
                                Toast.makeText(MainActivity.this, "Baggage limit exceeded",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                //I forgot to add units at the end in class, so have added
                                //it here
                                outputWt = inputWt*2.2;
                                txtViewResults
                                        .setText(String.format("Converted Wt: %.2f Lbs", outputWt));
                            }
                        } else if (radGroupConv.getCheckedRadioButtonId() == R.id.radBtnLbsToKgs) {
                            if (inputWt > 1000){
                                Toast.makeText(MainActivity.this, "Baggage limit exceeded",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                //I forgot to add the unit in class
                                outputWt = inputWt / 2.2;
                                txtViewResults
                                        .setText(String.format("Converted Wt: %.2f Kgs", outputWt));
                            }
                        }
                    } catch (Exception ex){
                        ex.printStackTrace();
                        Log.d("WT DEMO",ex.getMessage());
                    }
                }
            }
        });


    }
}
