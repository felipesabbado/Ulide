package com.example.ulide.ui.createSpots;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
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
    private Button submit, myLocation;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private String lat, lng;

    public CreateSpotsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1 - Criar o location Manager para ir buscar a localização onde o nosso device está
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        // 2 - Criar um location Listener para detetar mudanças na nossa localização
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lat = String.valueOf(location.getLatitude());
                lng = String.valueOf(location.getLongitude());
                String msg="New Latitude: "+ lat + " New Longitude: "+ lng;
                Log.e("Location", msg);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        // 3 - Detectar se o utilizador nos deu permissões para obter a localização
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{ Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        } else {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0,
                    0,
                    locationListener
            );
        }
    }

    // 4 - Verificar se temos permissões ao executar a nossa Activity
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        0,
                        0,
                        locationListener
                );
            }
        }
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
        myLocation = binding.buttonMyLocation;

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
                        JSONArray test;
                        test = task.execute("https://ulide.herokuapp.com/api/spots").get();

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

        myLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                latitude.setText(lat);
                longitude.setText(lng);
            }
        });

        return root;
    }
}