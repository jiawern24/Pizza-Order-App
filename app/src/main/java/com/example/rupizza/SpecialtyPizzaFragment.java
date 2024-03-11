package com.example.rupizza;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import Pizzeria.Order;
import Pizzeria.OrderMaker;
import Pizzeria.Pizza;
import Pizzeria.PizzaMaker;
import Pizzeria.Size;

/**
 * Fragment for specialty pizza section.
 *
 * @author Frances Cortuna
 */
public class SpecialtyPizzaFragment extends Fragment implements RecyclerViewInterface {
    final private PizzaMaker pizzaMaker = PizzaMaker.getInstance();
    List<SpecialtyPizzas> specialtyPizzas = new ArrayList<>();
    RadioButton sizeSmall, sizeMedium, sizeLarge;
    RadioGroup sizeGroup;
    TextView priceText;
    SwitchCompat extraSauce, extraCheese;
    Pizza pizza;
    OrderMaker orderMaker = OrderMaker.getInstance();
    Button addToOrderBtn;
    TextInputEditText quantityInput;
    int quantity = 0;
    boolean isUpdatingQuantityText = false;

    /**
     * Creates the fragment view and sets up recycler view for specialty pizza
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return View for fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_specialty_pizza, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        SpecialtyPizzaViewAdapter specialtyPizzaViewAdapter = new SpecialtyPizzaViewAdapter(getActivity().getApplicationContext(), specialtyPizzas, this);
        initiateRecyclerView(recyclerView, specialtyPizzaViewAdapter);
        attachButtons(view);

        sizeGroup.setOnCheckedChangeListener((group, checkedId) -> handleSizeChange(checkedId));
        extraSauce.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            if(pizza == null) {
                handlePizzaNullError();
                extraSauce.setChecked(false);
                return;
            }
            pizza.setExtraSauce(isChecked);
            updatePizza();
        }));
        extraCheese.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            if(pizza == null) {
                handlePizzaNullError();
                extraCheese.setChecked(false);
                return;
            }
            pizza.setExtraCheese(isChecked);
            updatePizza();
        }));
        addToOrderBtn.setOnClickListener(v -> handleAddToOrder());
        quantityInput.setText(String.valueOf(quantity));
        quantityInput.addTextChangedListener(createTextWatcher());

        return view;
    }

    /**
     * Attaches all UI buttons to appropriate variables here
     * @param view Fragment view
     */
    private void attachButtons(View view) {
        sizeSmall = view.findViewById(R.id.sizeSmallButton);
        sizeMedium = view.findViewById(R.id.sizeMedButton);
        sizeLarge = view.findViewById(R.id.sizeLargeButton);
        priceText = view.findViewById(R.id.priceTextView);
        extraSauce = view.findViewById(R.id.extraSauceSwitch);
        extraCheese = view.findViewById(R.id.extraCheeseSwitch);
        sizeGroup = view.findViewById(R.id.sizeRadioGroup);
        addToOrderBtn = view.findViewById(R.id.addToOrderButton);
        quantityInput = view.findViewById(R.id.quantityInput);
    }

    /**
     * Initializes recycler view for specialty pizzas
     * @param recyclerView Recycler view for specialty pizzas
     */
    public void initiateRecyclerView(RecyclerView recyclerView, SpecialtyPizzaViewAdapter specialtyPizzaViewAdapter) {
        specialtyPizzas.add(SpecialtyPizzas.DELUXE);
        specialtyPizzas.add(SpecialtyPizzas.SUPREME);
        specialtyPizzas.add(SpecialtyPizzas.PEPPERONI);
        specialtyPizzas.add(SpecialtyPizzas.SEAFOOD);
        specialtyPizzas.add(SpecialtyPizzas.MEATZZA);
        specialtyPizzas.add(SpecialtyPizzas.MARGHERITA);
        specialtyPizzas.add(SpecialtyPizzas.BBQCHICKEN);
        specialtyPizzas.add(SpecialtyPizzas.HAWAIIAN);
        specialtyPizzas.add(SpecialtyPizzas.MEATLOVERS);
        specialtyPizzas.add(SpecialtyPizzas.CHICKENALFREDO);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(specialtyPizzaViewAdapter);
    }

    /**
     * When a new specialty pizza is clicked
     * @param position Position of pizza that is clicked
     */
    @Override
    public void onItemClick(int position) {
        SpecialtyPizzas selectedPizza = specialtyPizzas.get(position);
        String pizzaType = selectedPizza.getPizzaName().toLowerCase().replace(" ", "");
        pizzaMaker.createPizza(pizzaType);
        pizza = pizzaMaker.getPizza();
        quantity = 1;
        quantityInput.setText(String.valueOf(quantity));
    }

    /**
     * Updates size, extra settings, and prices whenever a change is made
     */
    private void updatePizza() {
        String size = pizza.getSize().toString().toLowerCase();
        switch(size) {
            case "medium":
                sizeMedium.setChecked(true);
                break;
            case "large":
                sizeLarge.setChecked(true);
                break;
            default:
                sizeSmall.setChecked(true);
        }

        extraSauce.setChecked(pizza.isExtraSauce());
        extraCheese.setChecked(pizza.isExtraCheese());

        priceText.setText(String.format(Locale.US, "Price: $%.2f", pizza.price() * quantity));
    }

    /**
     * Called when user changes size selection
     * @param checkedId Id of radio button selected
     */
    private void handleSizeChange(int checkedId) {
        if(pizza == null) {
            handlePizzaNullError();
            sizeSmall.setChecked(false);
            sizeMedium.setChecked(false);
            sizeLarge.setChecked(false);
            return;
        }
        if(checkedId == R.id.sizeSmallButton) pizza.setSize(Size.SMALL);
        if(checkedId == R.id.sizeMedButton) pizza.setSize(Size.MEDIUM);
        if(checkedId == R.id.sizeLargeButton) pizza.setSize(Size.LARGE);

        updatePizza();
    }

    /**
     * Called when user tries to customize pizza without selecting type first
     */
    private void handlePizzaNullError() {
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setMessage("Select specialty pizza first.").setTitle("Error!");
        alert.setPositiveButton("OK", ((dialog, which) -> { }
        ));
        alert.create().show();
    }

    /**
     * Called when adding a pizza to order, creates a Toast to alert user
     */
    private void handleAddToOrder() {
        if(pizza == null || quantity == 0) {
            handlePizzaNullError();
            return;
        }
        if(orderMaker.getOrder() == null) orderMaker.createOrder();
        Order order = orderMaker.getOrder();
        while(quantity > 0) {
            order.addPizza(pizza);
            quantity--;
        }
        Toast.makeText(getContext(),"Pizza(s) added successfully!", Toast.LENGTH_LONG).show();
    }

    /**
     * Called when user changes the input edit text for quantity of pizzas
     * @return TextWatcher object that observes changes to text content
     */
    private TextWatcher createTextWatcher() {
        return new TextWatcher() {
            /**
             * This method is called to notify you that somewhere within `s`, the text has been changed.
             * @param s      The text that has been changed.
             * @param start  The starting point of the changed part in `s`.
             * @param count The length of the text that has been replaced.
             * @param after  The length of the new text that has replaced the old text.
             */
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            /**
             * This method is called to notify you that somewhere within `s`, the text has been changed.
             * @param s      The text that has been changed.
             * @param start  The starting point of the changed part in `s`.
             * @param before The length of the text that has been replaced.
             * @param count  The length of the new text that has replaced the old text.
             */
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            /**
             * This method is called to notify you that somewhere within `s`, the text has been changed.
             * @param s The `Editable` that has been changed.
             */
            @Override
            public void afterTextChanged(Editable s) {
                if(!isUpdatingQuantityText) {
                    try {
                        quantity = Integer.parseInt(s.toString());
                    } catch(NumberFormatException e) {
                        quantity = 0;
                    }
                    if(checkNullPizzaAfterQuantityChange()) return;
                    Toast.makeText(getContext(),"Quantity changed to " + quantity + " pizzas", Toast.LENGTH_SHORT).show();
                    updatePizza();
                }
            }
        };
    }

    /**
     * Checks for a null pizza after a quantity input change by user
     */
    private boolean checkNullPizzaAfterQuantityChange() {
        if(pizza == null) {
            isUpdatingQuantityText = true;
            quantity = 0;
            quantityInput.setText(String.valueOf(quantity));
            isUpdatingQuantityText = false;
            handlePizzaNullError();
            return true;
        }

        return false;
    }
}