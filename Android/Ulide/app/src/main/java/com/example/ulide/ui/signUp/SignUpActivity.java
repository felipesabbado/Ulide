package com.example.ulide.ui.signUp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ulide.MainActivity;
import com.example.ulide.R;
import com.example.ulide.data.LoginDataSource;
import com.example.ulide.downloaders.PostData;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {


    Button signUp;
    ArrayAdapter<String> adapterGender;
    ArrayList<String> listGender;

    EditText name;
    Spinner gender;
    EditText birthDate;
    EditText username;
    EditText email;
    EditText password;

    String postBDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.editTextName);
        signUp = findViewById(R.id.buttonSubmitSignUp);
        birthDate = findViewById(R.id.editTextBDate);
        gender = findViewById(R.id.spinnerGender);
        username = findViewById(R.id.editTextUserName);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);



        listGender = new ArrayList<>();

        listGender.add("Gender");
        listGender.add("Male");
        listGender.add("Female");
        listGender.add("Binary");


        adapterGender = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listGender);
        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapterGender);



        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);



        birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SignUpActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;

                        String yearString = String.valueOf(year);

                        String monthString = String.valueOf(month);
                        if (month<10){
                            monthString = "0"+monthString;
                        }

                        String dayString = String.valueOf(day);
                        if (day<10){
                            dayString = "0"+dayString;
                        }

                        postBDate = yearString+"-"+monthString+"-"+dayString;
                        String date = dayString+"/"+monthString+"/"+yearString;
                        birthDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    signUp.setTextColor(Color.BLUE);

                    if (name.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Favor preencher o campo em vermelho", Toast.LENGTH_SHORT).show();
                        name.setHintTextColor(Color.RED);
                    }
                    if (birthDate.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Favor preencher o campo em vermelho", Toast.LENGTH_SHORT).show();
                        birthDate.setHintTextColor(Color.RED);
                    }
                    if (username.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Favor preencher o campo em vermelho", Toast.LENGTH_SHORT).show();
                        username.setHintTextColor(Color.RED);
                    }
                    if (email.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Favor preencher o campo em vermelho", Toast.LENGTH_SHORT).show();
                        email.setHintTextColor(Color.RED);
                    }
                    if (password.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Favor preencher o campo em vermelho", Toast.LENGTH_SHORT).show();
                        password.setHintTextColor(Color.RED);
                    } else {
                        Map<String, String> postData = new HashMap<>();
                        postData.put("usName", name.getText().toString());
                        postData.put("usBdate", postBDate);
                        postData.put("usGender", "M");
                        postData.put("usEmail", email.getText().toString());
                        postData.put("usUsername", username.getText().toString());
                        postData.put("usPassword", password.getText().toString());

                        JSONArray arr;
                        PostData task = new PostData(postData);
                        arr = task.execute("https://ulide.herokuapp.com/api/users").get();

                        Toast.makeText(getApplicationContext(), "Welcom !"+ username.getText().toString(), Toast.LENGTH_SHORT).show();


                        LoginDataSource login = new LoginDataSource();
                        login.login(""+username.getText().toString(), ""+password.getText().toString());
                        Log.e("Id Sign up activity", ""+LoginDataSource.ID);
                        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}