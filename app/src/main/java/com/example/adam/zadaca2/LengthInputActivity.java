package com.example.adam.zadaca2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class LengthInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length_input);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.metre, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner fromSpinner = findViewById(R.id.spFrom_Spinner);
        Spinner toSpinner = findViewById(R.id.spTo_Spinner);

        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);
    }

    public void convert (View view){
        Spinner fromSpinner, toSpinner;
        EditText fromEditText, toEditText;

        fromSpinner = findViewById(R.id.spFrom_Spinner);
        toSpinner = findViewById(R.id.spTo_Spinner);
        fromEditText = findViewById(R.id.etText_From);
        toEditText=findViewById(R.id.etText_To);

        String fromString = (String) fromSpinner.getSelectedItem();
        String toString = (String) toSpinner.getSelectedItem();
        double input = Double.valueOf(fromEditText.getText().toString());

        LengthInputActivity.Unit fromUnit = LengthInputActivity.Unit.fromString(fromString);
        LengthInputActivity.Unit toUnit = LengthInputActivity.Unit.fromString(toString);

        LengthInputActivity converter = new LengthInputActivity(fromUnit, toUnit);
        double result = converter.convert(input);
        toEditText.setText(String.valueOf(result));



    }

    public LengthInputActivity(){}

    public enum Unit {
        MILIMETER,
        CENTIMETER,
        METER,
        KILOMETER;

        public static Unit fromString(String text) {
            if (text != null) {
                for (Unit unit : Unit.values()) {
                    if (text.equalsIgnoreCase(unit.toString())) {
                        return unit;
                    }
                }
            }
            throw new IllegalArgumentException("Cannot find value for " + text);
        }
    }

    private double multiplier;

    public LengthInputActivity(Unit from, Unit to) {
        double constant = 1;

        switch (from) {
            case MILIMETER:
                if (to == Unit.CENTIMETER) {
                    constant = 0.1;
                } else if (to == Unit.METER) {
                    constant = 0.001;
                } else if (to == Unit.KILOMETER) {
                    constant = 1e-6;
                }
                break;
            case CENTIMETER:
                if (to == Unit.MILIMETER) {
                    constant = 10;
                } else if (to == Unit.METER) {
                    constant = 0.01;
                } else if (to == Unit.KILOMETER) {
                    constant = 1e-5;
                }
                break;
            case METER:
                if (to == Unit.MILIMETER) {
                    constant = 1000;
                } else if (to == Unit.CENTIMETER) {
                    constant = 100;
                } else if (to == Unit.KILOMETER) {
                    constant = 0.001;
                }
                break;
            case KILOMETER:
                if (to == Unit.MILIMETER) {
                    constant = 1e+6;
                } else if (to == Unit.CENTIMETER) {
                    constant = 100000;
                } else if (to == Unit.METER) {
                    constant = 1000;
                }
                break;

        }
        multiplier = constant;
    }

    public double convert(double input) {
        return input * multiplier;
    }
}
