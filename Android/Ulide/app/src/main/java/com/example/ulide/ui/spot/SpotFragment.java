package com.example.ulide.ui.spot;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ulide.R;
import com.example.ulide.databinding.FragmentSpotBinding;
import com.example.ulide.downloaders.JSONObjDownloader;
import com.example.ulide.ui.spotsFromRoute.RecycleViewSpotsFromRoutesFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class SpotFragment extends Fragment implements OnMapReadyCallback {


    private GoogleMap mMap;
    private FragmentSpotBinding binding;
    private String idSpot;
    private JSONObject jsonObjectSpot;
//    PlacesClient placesClient = Places.createClient(getActivity());

    protected static String spotName;
    protected String spotLat;
    protected String spotLong;
    protected String spotBio;


    private TextView editTextSpotName;
    private TextView editTextSpotBio;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        idSpot = RecycleViewSpotsFromRoutesFragment.ID_SPOT;


        JSONObjDownloader task = new JSONObjDownloader();




    }

    // Define a Place ID.
    final String placeId = "Palacio de belem";

    // Specify the fields to return.
    final List<Place.Field> placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME);

    // Construct a request object, passing the place ID and fields array.
    final FetchPlaceRequest request = FetchPlaceRequest.newInstance(placeId, placeFields);


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSpotBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapSpots);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        editTextSpotName = binding.textViewSpotName;
        editTextSpotBio = binding.textViewDescription;

        getJSON(idSpot);

        editTextSpotName.setText(spotName);
        editTextSpotBio.setText(spotBio);



//        placesClient.fetchPlace(request).addOnSuccessListener((response) -> {
//            Place place = response.getPlace();
//            Log.i("Place", "Place found: " + place.getName());
//        }).addOnFailureListener((exception) -> {
//            if (exception instanceof ApiException) {
//                final ApiException apiException = (ApiException) exception;
//                Log.e("Place", "Place not found: " + exception.getMessage());
//                final int statusCode = apiException.getStatusCode();
//                // TODO: Handle error with given status code.
//            }
//        });

        return root;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.setBuildingsEnabled(false);

        LatLng lisbon = new LatLng(38.736946, -9.142685);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lisbon, 11));
    }

    public void getJSON(String id){
        JSONObjDownloader task = new JSONObjDownloader();
        String url = "https://ulide.herokuapp.com/api/spots/" + id    ;
        try {
            jsonObjectSpot = task.execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            jsonObjectSpot = null;
        }

        if (jsonObjectSpot != null) {
            for (int i = 0; i < jsonObjectSpot.length(); i++) {
                try {
                    spotName = jsonObjectSpot.getString("spName");
                    Log.e("INFO teste 1", spotName);
                    Log.e("INFO", ""+id);
                    spotLat = jsonObjectSpot.getString("spLat");
                    spotLong = jsonObjectSpot.getString("spLong");
                    spotBio = jsonObjectSpot.getString("spBio");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}