package com.example.adam.zadaca2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class WeightInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_input);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.grams, android.R.layout.simple_spinner_item);
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

        WeightInputActivity.Unit fromUnit = WeightInputActivity.Unit.fromString(fromString);
        WeightInputActivity.Unit toUnit = WeightInputActivity.Unit.fromString(toString);

        WeightInputActivity converter = new WeightInputActivity(fromUnit, toUnit);
        double result = converter.convert(input);
        toEditText.setText(String.valueOf(result));



    }

    public WeightInputActivity(){}

    public enum Unit {
        G,
        KG,
        TON;

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

    public WeightInputActivity(Unit from, Unit to) {
        double constant = 1;

        switch (from) {
            case G:
                if (to == Unit.KG) {
                    constant = 0.001;
                } else if (to == Unit.TON) {
                    constant = 0.000001;
                }
                break;
            case KG:
                if (to == Unit.G) {
                    constant = 1000;
                } else if (to == Unit.TON) {
                    constant = 0.001;
                }
                break;
            case TON:
                if (to == Unit.G) {
                    constant = 1000000;
                } else if (to == Unit.KG) {
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
