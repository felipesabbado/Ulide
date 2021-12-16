//package com.example.ulide.ui.startRoute;
//
//import static com.codingwithmitch.googlemaps2018.Constants.MAPVIEW_BUNDLE_KEY;
//
//import android.animation.ObjectAnimator;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.RelativeLayout;
//
//import com.codingwithmitch.googlemaps2018.R;
//import com.codingwithmitch.googlemaps2018.adapters.UserRecyclerAdapter;
//import com.codingwithmitch.googlemaps2018.models.ClusterMarker;
//import com.codingwithmitch.googlemaps2018.models.User;
//import com.codingwithmitch.googlemaps2018.models.UserLocation;
//import com.codingwithmitch.googlemaps2018.util.MyClusterManagerRenderer;
//import com.codingwithmitch.googlemaps2018.util.ViewWeightAnimationWrapper;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.MapView;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.LatLngBounds;
//import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.maps.DirectionsApiRequest;
//import com.google.maps.GeoApiContext;
//import com.google.maps.PendingResult;
//import com.google.maps.android.clustering.ClusterManager;
//import com.google.maps.model.DirectionsResult;
//
//import java.util.ArrayList;
//
//public class UserListFragment extends Fragment implements
//        OnMapReadyCallback,
//        View.OnClickListener,
//        GoogleMap.OnInfoWindowClickListener
//{
//
//    private static final String TAG = "UserListFragment";
//    private static final int MAP_LAYOUT_STATE_CONTRACTED = 0;
//    private static final int MAP_LAYOUT_STATE_EXPANDED = 1;
//
//
//    //widgets
//    private RecyclerView mUserListRecyclerView;
//    private MapView mMapView;
//    private RelativeLayout mMapContainer;
//
//
//    //vars
//    private ArrayList<User> mUserList = new ArrayList<>();
//    private ArrayList<UserLocation> mUserLocations = new ArrayList<>();
//    private UserRecyclerAdapter mUserRecyclerAdapter;
//    private GoogleMap mGoogleMap;
//    private UserLocation mUserPosition;
//    private LatLngBounds mMapBoundary;
//    private ClusterManager<ClusterMarker> mClusterManager;
//    private MyClusterManagerRenderer mClusterManagerRenderer;
//    private ArrayList<ClusterMarker> mClusterMarkers = new ArrayList<>();
//    private int mMapLayoutState = 0;
//    private GeoApiContext mGeoApiContext;
//
//
//    public static UserListFragment newInstance() {
//        return new UserListFragment();
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (mUserLocations.size() == 0) { // make sure the list doesn't duplicate by navigating back
//            if (getArguments() != null) {
//                final ArrayList<User> users = getArguments().getParcelableArrayList(getString(R.string.intent_user_list));
//                mUserList.addAll(users);
//
//                final ArrayList<UserLocation> locations = getArguments().getParcelableArrayList(getString(R.string.intent_user_locations));
//                mUserLocations.addAll(locations);
//            }
//        }
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
//        mUserListRecyclerView = view.findViewById(R.id.user_list_recycler_view);
//        mMapView = view.findViewById(R.id.user_list_map);
//        view.findViewById(R.id.btn_full_screen_map).setOnClickListener(this);
//        mMapContainer = view.findViewById(R.id.map_container);
//
//        initUserListRecyclerView();
//        initGoogleMap(savedInstanceState);
//
//        setUserPosition();
//
//        return view;
//    }
//
//    private Handler mHandler = new Handler();
//    private Runnable mRunnable;
//    private static final int LOCATION_UPDATE_INTERVAL = 3000;
//
//    private void startUserLocationsRunnable(){
//        Log.d(TAG, "startUserLocationsRunnable: starting runnable for retrieving updated locations.");
//        mHandler.postDelayed(mRunnable = new Runnable() {
//            @Override
//            public void run() {
//                retrieveUserLocations();
//                mHandler.postDelayed(mRunnable, LOCATION_UPDATE_INTERVAL);
//            }
//        }, LOCATION_UPDATE_INTERVAL);
//    }
//
//    private void stopLocationUpdates(){
//        mHandler.removeCallbacks(mRunnable);
//    }
//
//    private void retrieveUserLocations(){
//        Log.d(TAG, "retrieveUserLocations: retrieving location of all users in the chatroom.");
//
//        try{
//            for(final ClusterMarker clusterMarker: mClusterMarkers){
//
//                DocumentReference userLocationRef = FirebaseFirestore.getInstance()
//                        .collection(getString(R.string.collection_user_locations))
//                        .document(clusterMarker.getUser().getUser_id());
//
//                userLocationRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                        if(task.isSuccessful()){
//
//                            final UserLocation updatedUserLocation = task.getResult().toObject(UserLocation.class);
//
//                            // update the location
//                            for (int i = 0; i < mClusterMarkers.size(); i++) {
//                                try {
//                                    if (mClusterMarkers.get(i).getUser().getUser_id().equals(updatedUserLocation.getUser().getUser_id())) {
//
//                                        LatLng updatedLatLng = new LatLng(
//                                                updatedUserLocation.getGeo_point().getLatitude(),
//                                                updatedUserLocation.getGeo_point().getLongitude()
//                                        );
//
//                                        mClusterMarkers.get(i).setPosition(updatedLatLng);
//                                        mClusterManagerRenderer.setUpdateMarker(mClusterMarkers.get(i));
//                                    }
//
//
//                                } catch (NullPointerException e) {
//                                    Log.e(TAG, "retrieveUserLocations: NullPointerException: " + e.getMessage());
//                                }
//                            }
//                        }
//                    }
//                });
//            }
//        }catch (IllegalStateException e){
//            Log.e(TAG, "retrieveUserLocations: Fragment was destroyed during Firestore query. Ending query." + e.getMessage() );
//        }
//
//    }
//
//    private void addMapMarkers(){
//
//        if(mGoogleMap != null){
//
//            if(mClusterManager == null){
//                mClusterManager = new ClusterManager<ClusterMarker>(getActivity().getApplicationContext(), mGoogleMap);
//            }
//            if(mClusterManagerRenderer == null){
//                mClusterManagerRenderer = new MyClusterManagerRenderer(
//                        getActivity(),
//                        mGoogleMap,
//                        mClusterManager
//                );
//                mClusterManager.setRenderer(mClusterManagerRenderer);
//            }
//            mGoogleMap.setOnInfoWindowClickListener(this);
//
//            for(UserLocation userLocation: mUserLocations){
//
//                Log.d(TAG, "addMapMarkers: location: " + userLocation.getGeo_point().toString());
//                try{
//                    String snippet = "";
//                    if(userLocation.getUser().getUser_id().equals(FirebaseAuth.getInstance().getUid())){
//                        snippet = "This is you";
//                    }
//                    else{
//                        snippet = "Determine route to " + userLocation.getUser().getUsername() + "?";
//                    }
//
//                    int avatar = R.drawable.cartman_cop; // set the default avatar
//                    try{
//                        avatar = Integer.parseInt(userLocation.getUser().getAvatar());
//                    }catch (NumberFormatException e){
//                        Log.d(TAG, "addMapMarkers: no avatar for " + userLocation.getUser().getUsername() + ", setting default.");
//                    }
//                    ClusterMarker newClusterMarker = new ClusterMarker(
//                            new LatLng(userLocation.getGeo_point().getLatitude(), userLocation.getGeo_point().getLongitude()),
//                            userLocation.getUser().getUsername(),
//                            snippet,
//                            avatar,
//                            userLocation.getUser()
//                    );
//                    mClusterManager.addItem(newClusterMarker);
//                    mClusterMarkers.add(newClusterMarker);
//
//                }catch (NullPointerException e){
//                    Log.e(TAG, "addMapMarkers: NullPointerException: " + e.getMessage() );
//                }
//
//            }
//            mClusterManager.cluster();
//
//            setCameraView();
//        }
//    }
//
//    /**
//     * Determines the view boundary then sets the camera
//     * Sets the view
//     */
//    private void setCameraView() {
//
//        // Set a boundary to start
//        double bottomBoundary = mUserPosition.getGeo_point().getLatitude() - .1;
//        double leftBoundary = mUserPosition.getGeo_point().getLongitude() - .1;
//        double topBoundary = mUserPosition.getGeo_point().getLatitude() + .1;
//        double rightBoundary = mUserPosition.getGeo_point().getLongitude() + .1;
//
//        mMapBoundary = new LatLngBounds(
//                new LatLng(bottomBoundary, leftBoundary),
//                new LatLng(topBoundary, rightBoundary)
//        );
//
//        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(mMapBoundary, 0));
//    }
//
//    private void setUserPosition() {
//        for (UserLocation userLocation : mUserLocations) {
//            if (userLocation.getUser().getUser_id().equals(FirebaseAuth.getInstance().getUid())) {
//                mUserPosition = userLocation;
//            }
//        }
//    }
//
//    private void initGoogleMap(Bundle savedInstanceState) {
//        // *** IMPORTANT ***
//        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
//        // objects or sub-Bundles.
//        Bundle mapViewBundle = null;
//        if (savedInstanceState != null) {
//            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
//        }
//
//        mMapView.onCreate(mapViewBundle);
//
//        mMapView.getMapAsync(this);
//
//        if(mGeoApiContext == null){
//            mGeoApiContext = new GeoApiContext.Builder()
//                    .apiKey(getString(R.string.google_maps_api_key))
//                    .build();
//        }
//    }
//
//    private void initUserListRecyclerView() {
//        mUserRecyclerAdapter = new UserRecyclerAdapter(mUserList);
//        mUserListRecyclerView.setAdapter(mUserRecyclerAdapter);
//        mUserListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
//        if (mapViewBundle == null) {
//            mapViewBundle = new Bundle();
//            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
//        }
//
//        mMapView.onSaveInstanceState(mapViewBundle);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        mMapView.onResume();
//        startUserLocationsRunnable(); // update user locations every 'LOCATION_UPDATE_INTERVAL'
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        mMapView.onStart();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        mMapView.onStop();
//    }
//
//    @Override
//    public void onMapReady(GoogleMap map) {
////        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
////                != PackageManager.PERMISSION_GRANTED
////                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
////                != PackageManager.PERMISSION_GRANTED) {
////            return;
////        }
////        map.setMyLocationEnabled(true);
////        mGoogleMap = map;
////        setCameraView();
//
//        mGoogleMap = map;
//        addMapMarkers();
//    }
//
//    @Override
//    public void onPause() {
//        mMapView.onPause();
//        stopLocationUpdates(); // stop updating user locations
//        super.onPause();
//    }
//
//    @Override
//    public void onDestroy() {
//        mMapView.onDestroy();
//        super.onDestroy();
//    }
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        mMapView.onLowMemory();
//    }
//
//
//    private void expandMapAnimation(){
//        ViewWeightAnimationWrapper mapAnimationWrapper = new ViewWeightAnimationWrapper(mMapContainer);
//        ObjectAnimator mapAnimation = ObjectAnimator.ofFloat(mapAnimationWrapper,
//                "weight",
//                50,
//                100);
//        mapAnimation.setDuration(800);
//
//        ViewWeightAnimationWrapper recyclerAnimationWrapper = new ViewWeightAnimationWrapper(mUserListRecyclerView);
//        ObjectAnimator recyclerAnimation = ObjectAnimator.ofFloat(recyclerAnimationWrapper,
//                "weight",
//                50,
//                0);
//        recyclerAnimation.setDuration(800);
//
//        recyclerAnimation.start();
//        mapAnimation.start();
//    }
//
//    private void contractMapAnimation(){
//        ViewWeightAnimationWrapper mapAnimationWrapper = new ViewWeightAnimationWrapper(mMapContainer);
//        ObjectAnimator mapAnimation = ObjectAnimator.ofFloat(mapAnimationWrapper,
//                "weight",
//                100,
//                50);
//        mapAnimation.setDuration(800);
//
//        ViewWeightAnimationWrapper recyclerAnimationWrapper = new ViewWeightAnimationWrapper(mUserListRecyclerView);
//        ObjectAnimator recyclerAnimation = ObjectAnimator.ofFloat(recyclerAnimationWrapper,
//                "weight",
//                0,
//                50);
//        recyclerAnimation.setDuration(800);
//
//        recyclerAnimation.start();
//        mapAnimation.start();
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.btn_full_screen_map:{
//
//                if(mMapLayoutState == MAP_LAYOUT_STATE_CONTRACTED){
//                    mMapLayoutState = MAP_LAYOUT_STATE_EXPANDED;
//                    expandMapAnimation();
//                }
//                else if(mMapLayoutState == MAP_LAYOUT_STATE_EXPANDED){
//                    mMapLayoutState = MAP_LAYOUT_STATE_CONTRACTED;
//                    contractMapAnimation();
//                }
//                break;
//            }
//
//        }
//    }
//
//    @Override
//    public void onInfoWindowClick(final Marker marker) {
//        if(marker.getSnippet().equals("This is you")){
//            marker.hideInfoWindow();
//        }
//        else{
//
//            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//            builder.setMessage(marker.getSnippet())
//                    .setCancelable(true)
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
//                            calculateDirections(marker);
//                            dialog.dismiss();
//                        }
//                    })
//                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                        public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
//                            dialog.cancel();
//                        }
//                    });
//            final AlertDialog alert = builder.create();
//            alert.show();
//        }
//    }
//
//    private void calculateDirections(Marker marker){
//        Log.d(TAG, "calculateDirections: calculating directions.");
//
//        com.google.maps.model.LatLng destination = new com.google.maps.model.LatLng(
//                marker.getPosition().latitude,
//                marker.getPosition().longitude
//        );
//        DirectionsApiRequest directions = new DirectionsApiRequest(mGeoApiContext);
//
//        directions.alternatives(true);
//        directions.origin(
//                new com.google.maps.model.LatLng(
//                        mUserPosition.getGeo_point().getLatitude(),
//                        mUserPosition.getGeo_point().getLongitude()
//                )
//        );
//        Log.d(TAG, "calculateDirections: destination: " + destination.toString());
//        directions.destination(destination).setCallback(new PendingResult.Callback<DirectionsResult>() {
//            @Override
//            public void onResult(DirectionsResult result) {
//                Log.d(TAG, "onResult: routes: " + result.routes[0].toString());
//                Log.d(TAG, "onResult: geocodedWayPoints: " + result.geocodedWaypoints[0].toString());
//            }
//
//            @Override
//            public void onFailure(Throwable e) {
//                Log.e(TAG, "onFailure: " + e.getMessage() );
//
//            }
//        });
//    }
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
