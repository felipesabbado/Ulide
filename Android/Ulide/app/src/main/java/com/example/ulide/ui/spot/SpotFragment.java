package com.example.ulide.ui.spot;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ulide.R;
import com.example.ulide.databinding.FragmentSpotBinding;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SpotFragment extends Fragment implements OnMapReadyCallback {


    private GoogleMap mMap;
    private FragmentSpotBinding binding;
//    PlacesClient placesClient = Places.createClient(getActivity());



    public SpotFragment() {
        // Required empty public constructor
    }

    public static SpotFragment newInstance(String param1, String param2) {
        SpotFragment fragment = new SpotFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

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

        LatLng lisbon = new LatLng(38.736946, -9.142685);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lisbon, 11));
    }
}