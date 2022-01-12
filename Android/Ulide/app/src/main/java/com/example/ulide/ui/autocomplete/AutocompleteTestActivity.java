/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.ulide.ui.autocomplete;

import com.example.ulide.R;
import com.example.ulide.ui.autocomplete.FieldSelector;
import com.example.ulide.ui.autocomplete.StringUtil;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import timber.log.Timber;

/**
 * Activity for testing Autocomplete (activity and fragment widgets, and programmatic).
 */
public class AutocompleteTestActivity extends AppCompatActivity {

  private static final int AUTOCOMPLETE_REQUEST_CODE = 23487;
  private PlacesClient placesClient;
  private FieldSelector fieldSelector;
  protected ListView listViewPlaces;
  protected ArrayList<String> places;
  protected ArrayAdapter<String> adapterPlaces;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Use whatever theme was set from the MainActivity - some of these colors (e.g primary color)
    // will get picked up by the AutocompleteActivity.

    setContentView(R.layout.fragment_autocomplete);

    // Retrieve a PlacesClient (previously initialized - see MainActivity)
    placesClient = Places.createClient(this);

    // Set up view objects

    listViewPlaces = findViewById(R.id.list_places);
    places = new ArrayList<>();

    fieldSelector =
            new FieldSelector(
                    findViewById(R.id.use_custom_fields),
                    findViewById(R.id.custom_fields_list),
                    savedInstanceState);

    setupAutocompleteSupportFragment();

  }

  @Override
  protected void onSaveInstanceState(Bundle bundle) {
    super.onSaveInstanceState(bundle);
    fieldSelector.onSaveInstanceState(bundle);
  }

  private void setupAutocompleteSupportFragment() {
    final AutocompleteSupportFragment autocompleteSupportFragment =
            (AutocompleteSupportFragment)
                    getSupportFragmentManager().findFragmentById(R.id.autocomplete_support_fragment);
    autocompleteSupportFragment.setPlaceFields(getPlaceFields());
    autocompleteSupportFragment.setOnPlaceSelectedListener(getPlaceSelectionListener());

  }

  private PlaceSelectionListener getPlaceSelectionListener() {
    return new PlaceSelectionListener() {
      @Override
      public void onPlaceSelected(Place place) {
                Log.e("auto", StringUtil.stringifyAutocompleteWidget(place, isDisplayRawResultsChecked()));
                Log.e("INFOAUTO", place.getName());
                places.add(place.getName());
                InitalizeAdapter();
      }

      @Override
      public void onError(Status status) {
        System.out.println("hreneghfg");
      }
    };
  }

  /**
   * Called when AutocompleteActivity finishes
   */
  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
    // Required because this class extends AppCompatActivity which extends FragmentActivity
    // which implements this method to pass onActivityResult calls to child fragments
    // (eg AutocompleteFragment).
    super.onActivityResult(requestCode, resultCode, intent);
  }

  private void findAutocompletePredictions() {

    FindAutocompletePredictionsRequest.Builder requestBuilder =
            FindAutocompletePredictionsRequest.builder();

    if (isUseSessionTokenChecked()) {
      requestBuilder.setSessionToken(AutocompleteSessionToken.newInstance());
    }

    Task<FindAutocompletePredictionsResponse> task =
            placesClient.findAutocompletePredictions(requestBuilder.build());
  }

  //////////////////////////
  // Helper methods below //
  //////////////////////////

  boolean a = false;

  private List<Place.Field> getPlaceFields() {
    if (a) {
      return fieldSelector.getSelectedFields();
    } else {
      return fieldSelector.getAllFields();
    }
  }



  @Nullable
  private String getTextViewValue(@IdRes int textViewResId) {
    String value = ((TextView) findViewById(textViewResId)).getText().toString();
    return TextUtils.isEmpty(value) ? null : value;
  }

  private AutocompleteActivityMode getMode() {
    boolean isOverlayMode =
            a;
    return isOverlayMode ? AutocompleteActivityMode.OVERLAY : AutocompleteActivityMode.FULLSCREEN;
  }

  private boolean isDisplayRawResultsChecked() {
    return a;
  }

  private boolean isUseSessionTokenChecked() {
    return a;
  }



  private void showErrorAlert(@StringRes int messageResId) {
    new AlertDialog.Builder(this)
            .setTitle(R.string.error_alert_title)
            .setMessage(messageResId)
            .show();
  }

  public void InitalizeAdapter() {
    adapterPlaces = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, places);
    listViewPlaces.setAdapter(adapterPlaces);
  }
}
