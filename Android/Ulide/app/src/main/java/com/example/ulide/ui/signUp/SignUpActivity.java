package com.example.ulide.ui.signUp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ulide.R;
import com.example.ulide.downloaders.PostData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    EditText bDate;
    Button signUp;
    ArrayAdapter<String> adapterGender;
    ArrayList<String> listGender;

    EditText name;
    Spinner gender;
    EditText birthDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUp = findViewById(R.id.buttonSubmitSignUp);
        bDate = findViewById(R.id.editTextBDate);
        gender = findViewById(R.id.spinnerGender);

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



        bDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SignUpActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        bDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*try {
                    if (spotName.getText().toString().isEmpty()) {
                        Toast.makeText(getActivity(), "Favor preencher o campo em vermelho", Toast.LENGTH_SHORT).show();
                        spotName.setHintTextColor(Color.RED);
                    } else if (latitude.getText().toString().isEmpty()) {
                        Toast.makeText(getActivity(), "Favor preencher o campo em vermelho", Toast.LENGTH_SHORT).show();
                        latitude.setHintTextColor(Color.RED);
                    } else if (longitude.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(), "Favor preencher o campo em vermelho", Toast.LENGTH_SHORT).show();
                        longitude.setHintTextColor(Color.RED);
                    } else {
                        Map<String, String> postData = new HashMap<>();
                        postData.put("spName", spotName.getText().toString());
                        postData.put("spLat", latitude.getText().toString());
                        postData.put("spLong", longitude.getText().toString());

                        PostData task = new PostData(postData);
                        task.execute("https://ulide.herokuapp.com/api/spots");

                        Toast.makeText(getActivity(), "Local adicionado", Toast.LENGTH_SHORT).show();

                        spotName.setText("");
                        latitude.setText("");
                        longitude.setText("");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    spot = null;
                }*/
            }
        });

    }
}