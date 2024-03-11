package com.example.rupizza;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import Pizzeria.Order;
import Pizzeria.OrderMaker;
import Pizzeria.PizzaMaker;
import Pizzeria.Pizza;
import Pizzeria.Size;
import Pizzeria.Sauce;
import Pizzeria.Topping;

/**
 * Fragment for build your own pizza section
 *
 * @author Jia Wern Chong
 */

public class ByoPizzaFragment extends Fragment {
    final private PizzaMaker pizzaMaker = PizzaMaker.getInstance();
    private ArrayAdapter<String> adapter;
    Pizza pizza;
    Spinner byosizeSpinner;
    RadioButton tomatoButton, alfredoButton;
    RadioGroup sauceGroup;
    SwitchCompat extraSauce, extraCheese;
    ListView toppingsList;
    TextView priceText;
    OrderMaker orderMaker = OrderMaker.getInstance();
    Button addToOrderButton;

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
        View view = inflater.inflate(R.layout.fragment_byo_pizza, container, false);
        byosizeSpinner = view.findViewById(R.id.sizeSpinner);
        initializeSpinner();
        sauceGroup = view.findViewById(R.id.sauceRadioGroup);
        tomatoButton = view.findViewById(R.id.tomatoRadioButton);
        alfredoButton = view.findViewById(R.id.alfredoRadioButton);
        extraSauce = view.findViewById(R.id.byoExtraSauce);
        extraCheese = view.findViewById(R.id.byoExtraCheese);
        toppingsList = view.findViewById(R.id.toppingsListView);
        initializeListView();
        addToOrderButton = view.findViewById(R.id.addToOrderButton);
        priceText = view.findViewById(R.id.priceTextView);
        pizzaMaker.createPizza("byo");
        pizza = pizzaMaker.getPizza();
        sauceGroup.setOnCheckedChangeListener((group, checkId) -> handleSauceChange(checkId));

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
        addToOrderButton.setOnClickListener(v -> handleAddToOrder());
        return view;
    }

    /**
     * Initializes the spinner view with the different pizza sizes
     */
    private void initializeSpinner() {
        String[] items = {"Small","Medium","Large"};
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        byosizeSpinner.setAdapter(adapter);
        byosizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                handleSizeChange(item);
                Toast.makeText(getActivity(), item + " is selected", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * Initializes the list view with the different toppings to choose from
     */
    private void initializeListView() {
        ArrayList<String> arrayList = getList();
       adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_multiple_choice, arrayList);
       toppingsList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
       toppingsList.setAdapter(adapter);
       toppingsList.setOnItemClickListener((adapterView, view, position, id) -> {
           String item = adapterView.getItemAtPosition(position).toString();
           if (toppingsList.isItemChecked(position)){
               Toast.makeText(getActivity(), item + " is selected", Toast.LENGTH_SHORT).show();
               updateAddTopping(item);
               updatePizza();
           } else {
               updateRemoveTopping(item);
               updatePizza();
               Toast.makeText(getActivity(), item + " is deselected", Toast.LENGTH_SHORT).show();
           }
       });
    }

    /**
     * Method to handle pizza topping requirement,
     * user must select at least 3 toppings up to a maximum of 7 toppings
     * @return true if requirement is met and false otherwise
     */
    private boolean handleToppings() {
        if (toppingsList.getCheckedItemCount() < 3) {
            AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
            alert.setMessage("Must select at least 3 toppings!").setTitle("Error!");
            alert.setPositiveButton("OK", ((dialog, which) -> { }
            ));
            alert.create().show();
            return false;
        }
        else if (toppingsList.getCheckedItemCount() > 7) {
            AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
            alert.setMessage("Can only have a maximum of 7 toppings on one pizza!").setTitle("Error!");
            alert.setPositiveButton("OK", ((dialog, which) -> { }
            ));
            alert.create().show();
            return false;
        }
        return true;
    }

    /**
     * Method to retrieve list of toppings available,
     * only called when initializing toppings list view
     *
     * @return array list with the toppings
     */
    private ArrayList<String> getList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Sausage");
        arrayList.add("Pepperoni");
        arrayList.add("Beef");
        arrayList.add("Ham");
        arrayList.add("Shrimp");
        arrayList.add("Squid");
        arrayList.add("Crab meats");
        arrayList.add("Green pepper");
        arrayList.add("Onion");
        arrayList.add("Black olive");
        arrayList.add("Mushroom");
        arrayList.add("Bacon");
        arrayList.add("Chicken");
        arrayList.add("Mozzarella cheese");
        arrayList.add("Basil");
        arrayList.add("Pineapple");

        return arrayList;
    }

    /**
     * Called when user changes sauce of pizza
     * @param checkedId Id of the selected radio button
     */
    private void handleSauceChange(int checkedId) {
        if(checkedId == R.id.tomatoRadioButton)pizza.setSauce(Sauce.TOMATO);
        if(checkedId == R.id.alfredoRadioButton)pizza.setSauce(Sauce.ALFREDO);

        updatePizza();
    }

    /**
     * Called when user changes size of pizza
     * @param item string of selected spinner item
     */
    private void handleSizeChange(String item) {
        if(pizza == null) {
            handlePizzaNullError();
            byosizeSpinner.setSelection(adapter.getPosition("Small"));
            return;
        }
        if (item.equals("Small")) pizza.setSize(Size.SMALL);
        if (item.equals("Medium")) pizza.setSize(Size.MEDIUM);
        if (item.equals("Large")) pizza.setSize(Size.LARGE);

        updatePizza();
    }

    /**
     * Updates size, sauce, extra settings, toppings and prices whenever a change is made
     */
    private void updatePizza(){
        String size = pizza.getSize().toString().toLowerCase();
        switch(size) {
            case "medium":
                byosizeSpinner.setSelection(adapter.getPosition("Medium"));
                break;
            case "large":
                byosizeSpinner.setSelection(adapter.getPosition("Large"));
                break;
            default:
                byosizeSpinner.setSelection(adapter.getPosition("Small"));
        }

        String sauce = pizza.getSauce().toString().toLowerCase();
        if (sauce.equals("alfredo")) {
            alfredoButton.setChecked(true);
        } else {
            tomatoButton.setChecked(true);
        }

        extraSauce.setChecked(pizza.isExtraSauce());
        extraCheese.setChecked(pizza.isExtraCheese());

        priceText.setText(String.format(Locale.US, " $%.2f", pizza.price()));
    }

    private void updateAddTopping(String item){
        String str = item.toUpperCase();
        str = str.replaceAll("\\s", "");
        pizza.addTopping(Topping.valueOf(str));
        priceText.setText(String.format(Locale.US, " $%.2f", pizza.price()));
    }
    private void updateRemoveTopping(String item){
        String str = item.toUpperCase();
        str = str.replaceAll("\\s", "");
        pizza.removeTopping(Topping.valueOf(str));
        priceText.setText(String.format(Locale.US, " $%.2f", pizza.price()));
    }

    /**
     * Clear all selections for topping list
     */
    private void resetToppingList(){
        toppingsList.setItemChecked(adapter.getPosition("Sausage"), false);
        toppingsList.setItemChecked(adapter.getPosition("Pepperoni"), false);
        toppingsList.setItemChecked(adapter.getPosition("Beef"), false);
        toppingsList.setItemChecked(adapter.getPosition("Ham"), false);
        toppingsList.setItemChecked(adapter.getPosition("Shrimp"), false);
        toppingsList.setItemChecked(adapter.getPosition("Squid"), false);
        toppingsList.setItemChecked(adapter.getPosition("Crab meats"), false);
        toppingsList.setItemChecked(adapter.getPosition("Green pepper"), false);
        toppingsList.setItemChecked(adapter.getPosition("Onion"), false);
        toppingsList.setItemChecked(adapter.getPosition("Black olive"), false);
        toppingsList.setItemChecked(adapter.getPosition("Mushroom"), false);
        toppingsList.setItemChecked(adapter.getPosition("Bacon"), false);
        toppingsList.setItemChecked(adapter.getPosition("Chicken"), false);
        toppingsList.setItemChecked(adapter.getPosition("Mozzarella cheese"), false);
        toppingsList.setItemChecked(adapter.getPosition("Basil"), false);
        toppingsList.setItemChecked(adapter.getPosition("Pineapple"), false);

        if (pizza.getToppings().contains(Topping.SAUSAGE)) pizza.removeTopping(Topping.SAUSAGE);
        if (pizza.getToppings().contains(Topping.PEPPERONI)) pizza.removeTopping(Topping.PEPPERONI);
        if (pizza.getToppings().contains(Topping.BEEF)) pizza.removeTopping(Topping.BEEF);
        if (pizza.getToppings().contains(Topping.HAM)) pizza.removeTopping(Topping.HAM);
        if (pizza.getToppings().contains(Topping.SHRIMP)) pizza.removeTopping(Topping.SHRIMP);
        if (pizza.getToppings().contains(Topping.SQUID)) pizza.removeTopping(Topping.SQUID);
        if (pizza.getToppings().contains(Topping.CRABMEATS)) pizza.removeTopping(Topping.CRABMEATS);
        if (pizza.getToppings().contains(Topping.GREENPEPPER)) pizza.removeTopping(Topping.GREENPEPPER);
        if (pizza.getToppings().contains(Topping.ONION)) pizza.removeTopping(Topping.ONION);
        if (pizza.getToppings().contains(Topping.BLACKOLIVE)) pizza.removeTopping(Topping.BLACKOLIVE);
        if (pizza.getToppings().contains(Topping.MUSHROOM)) pizza.removeTopping(Topping.MUSHROOM);
        if (pizza.getToppings().contains(Topping.BACON)) pizza.removeTopping(Topping.BACON);
        if (pizza.getToppings().contains(Topping.CHICKEN)) pizza.removeTopping(Topping.CHICKEN);
        if (pizza.getToppings().contains(Topping.MOZZARELLACHEESE)) pizza.removeTopping(Topping.MOZZARELLACHEESE);
        if (pizza.getToppings().contains(Topping.BASIL)) pizza.removeTopping(Topping.BASIL);
        if (pizza.getToppings().contains(Topping.PINEAPPLE)) pizza.removeTopping(Topping.PINEAPPLE);
    }

    /**
     * Called when adding a pizza to order, creates a Toast to alert user
     */
    private void handleAddToOrder() {
        if(pizza == null) {
            handlePizzaNullError();
            return;
        }
        if(orderMaker.getOrder() == null) orderMaker.createOrder();
        Order order = orderMaker.getOrder();

        if (handleToppings()) {
            order.addPizza(pizza);
            Toast.makeText(getContext(),"Pizza added successfully!", Toast.LENGTH_LONG).show();

            pizzaMaker.createPizza("byo");
            pizza = pizzaMaker.getPizza();
            byosizeSpinner.setSelection(adapter.getPosition("Small"));
            tomatoButton.setChecked(true);
            extraSauce.setChecked(false);
            extraCheese.setChecked(false);
            resetToppingList();
            updatePizza();
        }
    }

    private void handlePizzaNullError() {
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setMessage("Select options for pizza first.").setTitle("Error!");
        alert.setPositiveButton("OK", ((dialog, which) -> { }
        ));
        alert.create().show();
    }

}