package com.example.adam.zadaca2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConversionSelectActivity extends AppCompatActivity implements View.OnClickListener {

    Button bTemperature;
    Button bData;
    Button bWeight;
    Button bLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_select);
        this.setUpUI();
    }

    private void setUpUI() {
        this.bTemperature = findViewById(R.id.bTemperature);
        this.bData = findViewById(R.id.bData);
        this.bLength = findViewById(R.id.bLength);
        this.bWeight = findViewById(R.id.bWeight);
        this.bTemperature.setOnClickListener(this);
        this.bData.setOnClickListener(this);
        this.bLength.setOnClickListener(this);
        this.bWeight.setOnClickListener(this);


    }

    @Override
    public void onClick (View v){
        Intent explicitIntent;
        switch (v.getId())
        {
            case (R.id.bTemperature):
                explicitIntent = new Intent ();
                explicitIntent.setClass(getApplicationContext(), TemperatureInputActivity.class);
                this.startActivity(explicitIntent);
                break;
            case (R.id.bData):
                explicitIntent = new Intent();
                explicitIntent.setClass(getApplicationContext(), DataInputActivity.class);
                this.startActivity(explicitIntent);
                break;
            case (R.id.bWeight):
                explicitIntent=new Intent();
                explicitIntent.setClass(getApplicationContext(),WeightInputActivity.class);
                this.startActivity(explicitIntent);
                break;
            case (R.id.bLength):
                explicitIntent=new Intent();
                explicitIntent.setClass(getApplicationContext(),LengthInputActivity.class);
                this.startActivity(explicitIntent);
                break;
        }
    }

}
