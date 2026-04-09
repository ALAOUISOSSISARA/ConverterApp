package com.example.converterapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TemperatureFragment extends Fragment {

    // ── Step 1: declare all Views as private fields ──────────────────────
    private EditText editTextTemp;
    private RadioGroup radioGroupTemp;
    private Button buttonConvertTemp;
    private TextView textViewResultTemp;

    // ── Step 2: inflate the layout and wire everything up ─────────────────
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate: convert fragment_temperature.xml into a real View object
        View view = inflater.inflate(
                R.layout.fragment_temperature,
                container,
                false
        );

        // Find each View inside the inflated layout by its ID
        editTextTemp        = view.findViewById(R.id.editTextTemp);
        radioGroupTemp      = view.findViewById(R.id.radioGroupTemp);
        buttonConvertTemp   = view.findViewById(R.id.buttonConvertTemp);
        textViewResultTemp  = view.findViewById(R.id.textViewResultTemp);

        // Attach a click listener to the Convert button
        buttonConvertTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTemperature();
            }
        });

        // Return the fully configured view to Android
        return view;
    }

    // ── Step 3: the conversion logic ──────────────────────────────────────
    private void convertTemperature() {

        // Read what the user typed and remove accidental spaces
        String input = editTextTemp.getText().toString().trim();

        // Guard: if the field is empty, show a message and stop
        if (input.isEmpty()) {
            textViewResultTemp.setText("Please enter a value.");
            return;
        }

        // Convert the String input into a decimal number
        double value = Double.parseDouble(input);

        // Find out which RadioButton is currently selected
        int selectedId = radioGroupTemp.getCheckedRadioButtonId();

        double result;
        String resultText;

        if (selectedId == R.id.radioCtoF) {
            // Celsius → Fahrenheit formula
            result = (value * 9.0 / 5.0) + 32;
            resultText = value + " °C  =  " + result + " °F";
        } else {
            // Fahrenheit → Celsius formula
            result = (value - 32) * 5.0 / 9.0;
            resultText = value + " °F  =  " + result + " °C";
        }

        // Display the result on screen
        textViewResultTemp.setText("Result: " + resultText);
    }
}