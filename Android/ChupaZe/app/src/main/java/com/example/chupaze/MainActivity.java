package com.example.chupaze;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.chupaze.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    public Spinner spinnerCountries, spinnerCities;

    private String[] countries = {"Portugal", "Espanha"};
    private String[] portugal = {"Lisboa", "Porto", "Faro"};
    private String[] espanha = {"Madrid", "Barcelona", "Valencia"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        spinnerCountries = (Spinner) findViewById(R.id.spinnerCountries);
        spinnerCities = (Spinner) findViewById(R.id.spinnerCities);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        try {
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
        }catch (Exception e){
            System.out.println("Nao deu");
        }
    }

}