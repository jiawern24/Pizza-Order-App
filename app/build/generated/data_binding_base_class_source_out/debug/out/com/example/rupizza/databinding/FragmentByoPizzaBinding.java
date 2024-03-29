// Generated by view binder compiler. Do not edit!
package com.example.rupizza.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.rupizza.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentByoPizzaBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final Button addToOrderButton;

  @NonNull
  public final RadioButton alfredoRadioButton;

  @NonNull
  public final SwitchCompat byoExtraCheese;

  @NonNull
  public final SwitchCompat byoExtraSauce;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final TextView priceTextView;

  @NonNull
  public final RadioGroup sauceRadioGroup;

  @NonNull
  public final Spinner sizeSpinner;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView textView4;

  @NonNull
  public final TextView textView5;

  @NonNull
  public final TextView textView6;

  @NonNull
  public final TextView textView7;

  @NonNull
  public final RadioButton tomatoRadioButton;

  @NonNull
  public final ListView toppingsListView;

  private FragmentByoPizzaBinding(@NonNull FrameLayout rootView, @NonNull Button addToOrderButton,
      @NonNull RadioButton alfredoRadioButton, @NonNull SwitchCompat byoExtraCheese,
      @NonNull SwitchCompat byoExtraSauce, @NonNull ImageView imageView2,
      @NonNull TextView priceTextView, @NonNull RadioGroup sauceRadioGroup,
      @NonNull Spinner sizeSpinner, @NonNull TextView textView, @NonNull TextView textView4,
      @NonNull TextView textView5, @NonNull TextView textView6, @NonNull TextView textView7,
      @NonNull RadioButton tomatoRadioButton, @NonNull ListView toppingsListView) {
    this.rootView = rootView;
    this.addToOrderButton = addToOrderButton;
    this.alfredoRadioButton = alfredoRadioButton;
    this.byoExtraCheese = byoExtraCheese;
    this.byoExtraSauce = byoExtraSauce;
    this.imageView2 = imageView2;
    this.priceTextView = priceTextView;
    this.sauceRadioGroup = sauceRadioGroup;
    this.sizeSpinner = sizeSpinner;
    this.textView = textView;
    this.textView4 = textView4;
    this.textView5 = textView5;
    this.textView6 = textView6;
    this.textView7 = textView7;
    this.tomatoRadioButton = tomatoRadioButton;
    this.toppingsListView = toppingsListView;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentByoPizzaBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentByoPizzaBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_byo_pizza, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentByoPizzaBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.addToOrderButton;
      Button addToOrderButton = ViewBindings.findChildViewById(rootView, id);
      if (addToOrderButton == null) {
        break missingId;
      }

      id = R.id.alfredoRadioButton;
      RadioButton alfredoRadioButton = ViewBindings.findChildViewById(rootView, id);
      if (alfredoRadioButton == null) {
        break missingId;
      }

      id = R.id.byoExtraCheese;
      SwitchCompat byoExtraCheese = ViewBindings.findChildViewById(rootView, id);
      if (byoExtraCheese == null) {
        break missingId;
      }

      id = R.id.byoExtraSauce;
      SwitchCompat byoExtraSauce = ViewBindings.findChildViewById(rootView, id);
      if (byoExtraSauce == null) {
        break missingId;
      }

      id = R.id.imageView2;
      ImageView imageView2 = ViewBindings.findChildViewById(rootView, id);
      if (imageView2 == null) {
        break missingId;
      }

      id = R.id.priceTextView;
      TextView priceTextView = ViewBindings.findChildViewById(rootView, id);
      if (priceTextView == null) {
        break missingId;
      }

      id = R.id.sauceRadioGroup;
      RadioGroup sauceRadioGroup = ViewBindings.findChildViewById(rootView, id);
      if (sauceRadioGroup == null) {
        break missingId;
      }

      id = R.id.sizeSpinner;
      Spinner sizeSpinner = ViewBindings.findChildViewById(rootView, id);
      if (sizeSpinner == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.textView4;
      TextView textView4 = ViewBindings.findChildViewById(rootView, id);
      if (textView4 == null) {
        break missingId;
      }

      id = R.id.textView5;
      TextView textView5 = ViewBindings.findChildViewById(rootView, id);
      if (textView5 == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = ViewBindings.findChildViewById(rootView, id);
      if (textView6 == null) {
        break missingId;
      }

      id = R.id.textView7;
      TextView textView7 = ViewBindings.findChildViewById(rootView, id);
      if (textView7 == null) {
        break missingId;
      }

      id = R.id.tomatoRadioButton;
      RadioButton tomatoRadioButton = ViewBindings.findChildViewById(rootView, id);
      if (tomatoRadioButton == null) {
        break missingId;
      }

      id = R.id.toppingsListView;
      ListView toppingsListView = ViewBindings.findChildViewById(rootView, id);
      if (toppingsListView == null) {
        break missingId;
      }

      return new FragmentByoPizzaBinding((FrameLayout) rootView, addToOrderButton,
          alfredoRadioButton, byoExtraCheese, byoExtraSauce, imageView2, priceTextView,
          sauceRadioGroup, sizeSpinner, textView, textView4, textView5, textView6, textView7,
          tomatoRadioButton, toppingsListView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
