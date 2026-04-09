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

public class DistanceFragment extends Fragment {

    // ── Step 1: declare all Views as private fields ───────────────────────
    private EditText editTextDist;
    private RadioGroup radioGroupDist;
    private Button buttonConvertDist;
    private TextView textViewResultDist;

    // ── Step 2: inflate the layout and wire everything up ─────────────────
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate: convert fragment_distance.xml into a real View object
        View view = inflater.inflate(
                R.layout.fragment_distance,
                container,
                false
        );

        // Find each View inside the inflated layout by its ID
        editTextDist        = view.findViewById(R.id.editTextDist);
        radioGroupDist      = view.findViewById(R.id.radioGroupDist);
        buttonConvertDist   = view.findViewById(R.id.buttonConvertDist);
        textViewResultDist  = view.findViewById(R.id.textViewResultDist);

        // Attach a click listener to the Convert button
        buttonConvertDist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertDistance();
            }
        });

        // Return the fully configured view to Android
        return view;
    }

    // ── Step 3: the conversion logic ──────────────────────────────────────
    private void convertDistance() {

        // Read what the user typed and remove accidental spaces
        String input = editTextDist.getText().toString().trim();

        // Guard: if the field is empty, show a message and stop
        if (input.isEmpty()) {
            textViewResultDist.setText("Please enter a value.");
            return;
        }

        // Convert the String input into a decimal number
        double value = Double.parseDouble(input);

        // Find out which RadioButton is currently selected
        int selectedId = radioGroupDist.getCheckedRadioButtonId();

        double result;
        String resultText;

        if (selectedId == R.id.radioKmToMiles) {
            // Kilometers → Miles formula
            result = value * 0.621371;
            resultText = value + " km  =  " + result + " miles";
        } else {
            // Miles → Kilometers formula
            result = value * 1.60934;
            resultText = value + " miles  =  " + result + " km";
        }

        // Display the result on screen
        textViewResultDist.setText("Result: " + resultText);
    }
}