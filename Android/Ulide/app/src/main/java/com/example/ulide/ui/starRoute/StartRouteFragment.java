package com.example.ulide.ui.starRoute;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.ulide.R;
import com.example.ulide.databinding.FragmentFindRoutesBinding;
import com.example.ulide.databinding.FragmentStartRouteBinding;
import com.example.ulide.models.DirectionResponses;
import com.example.ulide.models.LegsItem;
import com.example.ulide.ui.spotsFromRoute.RecycleViewSpotsFromRoutesFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.maps.android.PolyUtil;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import timber.log.Timber;

@SuppressLint("Registered")
public class StartRouteFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap map;
    private LatLng fkip;
    private LatLng monas;
    private ArrayList<LatLng> spotsPos;
    private TextView info;
    private int duration, distance;


    FragmentStartRouteBinding binding;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStartRouteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        spotsPos = RecycleViewSpotsFromRoutesFragment.spotsPos;

        info = binding.textViewDistAndTime;



        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.maps_start_route);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }


        return root;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        for (int i = 0; i < spotsPos.size()-1; i++) {

            fkip = spotsPos.get(i);
            monas = spotsPos.get(i+1);

            MarkerOptions markerFkip = new MarkerOptions()
                    .position(fkip)
                    .title("2");
            MarkerOptions markerMonas = new MarkerOptions()
                    .position(monas)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .title("1");
            map.addMarker(markerFkip);
            map.addMarker(markerMonas);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(monas, 11.6f));

            String fromFKIP = String.valueOf(fkip.latitude) + "," + String.valueOf(fkip.longitude);
            String toMonas = String.valueOf(monas.latitude) + "," + String.valueOf(monas.longitude);

            drawLine(fromFKIP, toMonas);
            LegsItem item = new LegsItem();

            Log.e("distancia", ""+ item.getDistance() );

        }
    }


    public void drawLine(String fromFKIP, String toMonas){
        ApiServices apiServices = RetrofitClient.apiServices(requireContext());
        apiServices.getDirection(fromFKIP, toMonas, "AIzaSyC3RwBupXyFdul5XtIAWjDsF9f8ogyLam4")
                .enqueue(new Callback<DirectionResponses>() {
                    @Override
                    public void onResponse(@NonNull Call<DirectionResponses> call, @NonNull Response<DirectionResponses> response) {
                        drawPolyline(response);
                        info.setText("Distance: " + distance/100 + "KM" + "   " + "Travel time: " + duration / 60 + "min");
                        Timber.d(response.message());
                    }

                    @Override
                    public void onFailure(@NonNull Call<DirectionResponses> call, @NonNull Throwable t) {
                        Timber.e(t.getLocalizedMessage());
                    }
                });
    }

    private void drawPolyline(@NonNull Response<DirectionResponses> response) {
        if (response.body() != null) {
            String shape = response.body().getRoutes().get(0).getOverviewPolyline().getPoints();
            distance = response.body().getRoutes().get(0).getLegs().get(0).getDistance().getValue();
            duration = response.body().getRoutes().get(0).getLegs().get(0).getDuration().getValue();
            Log.e("distanciaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", ""+distance);
            Log.e("durationnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn", ""+duration);
            PolylineOptions polyline = new PolylineOptions()
                    .addAll(PolyUtil.decode(shape))
                    .width(8f)
                    .color(Color.DKGRAY);
            map.addPolyline(polyline);
        }
    }

    private interface ApiServices {
        @GET("maps/api/directions/json")
        Call<DirectionResponses> getDirection(@Query("origin") String origin,
                                              @Query("destination") String destination,
                                              @Query("key") String apiKey);
    }

    private static class RetrofitClient {
        static ApiServices apiServices(Context context) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(context.getResources().getString(R.string.base_url_maps))
                    .build();

            return retrofit.create(ApiServices.class);
        }
    }
}
