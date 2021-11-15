package com.example.ulide.Actvities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ulide.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void MainMenu(View view) {
        Intent mainMenu = new Intent(getApplicationContext(), MainScreenActivity.class);
        startActivity(mainMenu);
    }
}