package com.example.chupaze;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class DashBoardActivity extends AppCompatActivity {

    Spinner spinnerCountries, spinnerCities;

    private String[] countries = {"Portugal", "Espanha"};
    private String[] portugal = {"Lisboa", "Porto", "Faro"};
    private String[] espanha = {"Madrid", "Barcelona", "Valencia"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dashboard);

        // BASEADO EM -> https://developer.android.com/guide/topics/ui/controls/spinner

        spinnerCountries = (Spinner) findViewById(R.id.spinnerCountries);
        spinnerCities = (Spinner) findViewById(R.id.spinnerCities);

        ArrayAdapter<String> spinnerArrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countries);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountries.setAdapter(spinnerArrayAdapter);

        spinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String countryName = spinnerCountries.getSelectedItem().toString();
                Log.i("PAIS ->", countryName);

                //Portugal
                if(position == 0){
                    ArrayAdapter<String> adapter= new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_spinner_item,portugal);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCities.setAdapter(adapter);
                }
                //Espanha
                if(position == 1){
                    ArrayAdapter<String> adapter= new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_spinner_item,espanha);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCities.setAdapter(adapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
