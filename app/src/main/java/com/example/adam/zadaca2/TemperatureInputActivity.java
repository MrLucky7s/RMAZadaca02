package com.example.adam.zadaca2;

import android.animation.TypeConverter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class TemperatureInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_input);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_item);
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

        TemperatureInputActivity.Unit fromUnit = TemperatureInputActivity.Unit.fromString(fromString);
        TemperatureInputActivity.Unit toUnit = TemperatureInputActivity.Unit.fromString(toString);

        TemperatureInputActivity converter = new TemperatureInputActivity(fromUnit, toUnit);
        double result = converter.convert(input);
        toEditText.setText(String.valueOf(result));



    }

    public TemperatureInputActivity(){}

    public enum Unit {
        CELSIUS,
        FARENHEIT,
        KELVIN;

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

    public TemperatureInputActivity(Unit from, Unit to) {
        double constant = 1;

        switch (from) {
            case KELVIN:
                if (to == Unit.CELSIUS) {
                    constant = 1+273.15;
                } else if (to == Unit.FARENHEIT) {
                    constant = 9/5-459.67;
                }
                break;
            case CELSIUS:
                if (to == Unit.FARENHEIT) {
                    constant = 9/5+32;
                } else if (to == Unit.KELVIN) {
                    constant = 1+273.15;
                }
                break;
            case FARENHEIT:
                if (to == Unit.KELVIN) {
                    constant = 459.67/5/9;
                } else if (to == Unit.CELSIUS) {
                    constant = -32*5/9;
                }
                break;

        }
        multiplier = constant;
    }

    public double convert(double input) {
        return input * multiplier;
    }
}
