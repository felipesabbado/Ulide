package com.example.ulide.ui.createSpots;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ulide.databinding.FragmentCreateSpotsBinding;
import com.example.ulide.downloaders.PostData;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class CreateSpotsFragment extends Fragment {

    private FragmentCreateSpotsBinding binding;
    private JSONArray spot = null;
    private EditText spotName, latitude, longitude;
    private Button submit;

    public CreateSpotsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCreateSpotsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        spotName = binding.editTextPersonName;
        latitude = binding.editTextLatitude;
        longitude = binding.editTextLongitude;
        submit = binding.buttonSubmit;

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
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
                }
            }
        });

        return root;
    }
}