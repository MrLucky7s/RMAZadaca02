package com.example.adam.zadaca2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class DataInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.bytes, android.R.layout.simple_spinner_item);
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

        DataInputActivity.Unit fromUnit = DataInputActivity.Unit.fromString(fromString);
        DataInputActivity.Unit toUnit = DataInputActivity.Unit.fromString(toString);

        DataInputActivity converter = new DataInputActivity(fromUnit, toUnit);
        double result = converter.convert(input);
        toEditText.setText(String.valueOf(result));



    }

    public DataInputActivity() {}

    public enum Unit {
        BYTE,
        KILOBYTE,
        MEGABYTE,
        GIGABYTE;

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

    public DataInputActivity(Unit from, Unit to) {
        double constant = 1;

        switch (from) {
            case BYTE:
                if (to == Unit.KILOBYTE) {
                    constant = 0.0009765625;
                } else if (to == Unit.MEGABYTE) {
                    constant = 9.53674316e-7;
                } else if (to == Unit.GIGABYTE) {
                    constant = 9.3132257e-10;
                }
                break;
            case KILOBYTE:
                if (to == Unit.BYTE) {
                    constant = 1024;
                } else if (to == Unit.MEGABYTE) {
                    constant = 0.0009765625;
                } else if (to == Unit.GIGABYTE) {
                    constant = 9.53674316e-7;
                }
                break;
            case MEGABYTE:
                if (to == Unit.BYTE) {
                    constant = 1048576;
                } else if (to == Unit.KILOBYTE) {
                    constant = 1024;
                } else if (to == Unit.GIGABYTE) {
                    constant = 0.0009765625;
                }
                break;
            case GIGABYTE:
                if (to == Unit.BYTE) {
                    constant = 1073741824;
                } else if (to == Unit.KILOBYTE) {
                    constant = 1048576;
                } else if (to == Unit.MEGABYTE) {
                    constant = 1024;
                }
                break;

        }
        multiplier = constant;
    }

    public double convert(double input) {
        return input * multiplier;
    }
}
