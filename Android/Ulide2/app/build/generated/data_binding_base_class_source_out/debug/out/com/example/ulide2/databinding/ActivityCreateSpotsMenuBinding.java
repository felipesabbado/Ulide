// Generated by view binder compiler. Do not edit!
package com.example.ulide2.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.ulide2.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityCreateSpotsMenuBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonRoutesTest1;

  @NonNull
  public final ImageView imageView;

  private ActivityCreateSpotsMenuBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button buttonRoutesTest1, @NonNull ImageView imageView) {
    this.rootView = rootView;
    this.buttonRoutesTest1 = buttonRoutesTest1;
    this.imageView = imageView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCreateSpotsMenuBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCreateSpotsMenuBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_create_spots_menu, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCreateSpotsMenuBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonRoutesTest1;
      Button buttonRoutesTest1 = ViewBindings.findChildViewById(rootView, id);
      if (buttonRoutesTest1 == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      return new ActivityCreateSpotsMenuBinding((ConstraintLayout) rootView, buttonRoutesTest1,
          imageView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}