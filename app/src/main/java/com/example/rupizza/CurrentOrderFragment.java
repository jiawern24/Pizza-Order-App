package com.example.rupizza;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import Pizzeria.Order;
import Pizzeria.OrderMaker;
import Pizzeria.Pizza;
import Pizzeria.StoreOrder;

/**
 * A simple {@link Fragment} subclass.
 * Shows the current order with list of pizzas, prices, and ability to remove pizza or place order
 *
 * @author Frances Cortuna
 */
public class CurrentOrderFragment extends Fragment implements RecyclerViewInterface {
    OrderMaker orderMaker = OrderMaker.getInstance();
    Order order = orderMaker.getOrder();
    StoreOrder storeOrder = StoreOrder.getInstance();
    TextView orderNumberTextView, subtotalTextView, salesTaxTextView, orderTotalTextView;
    Pizza pizzaToDelete;
    Button removePizzaBtn, placeOrderBtn, storeOrdersBtn;
    private CurrentOrderViewAdapter currentOrderViewAdapter;
    ArrayList<Pizza> pizzas;
    View view;

    /**
     * Creates fragment view and sets up recycler view and buttons
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return View to show
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_current_order, container, false);
        orderNumberTextView = view.findViewById(R.id.orderNumberTextView);
        subtotalTextView = view.findViewById(R.id.subtotalTextView);
        salesTaxTextView = view.findViewById(R.id.salesTaxTextView);
        orderTotalTextView = view.findViewById(R.id.orderTotalTextView);
        removePizzaBtn = view.findViewById(R.id.removePizzaButton);
        placeOrderBtn = view.findViewById(R.id.placeOrderButton);
        storeOrdersBtn = view.findViewById(R.id.storeOrdersButton);
        initializeRecyclerView();
        update();

        removePizzaBtn.setOnClickListener(v -> handleRemovePizza());
        placeOrderBtn.setOnClickListener(v -> handlePlaceOrder());
        storeOrdersBtn.setOnClickListener(v -> handleStoreOrder());
        return view;
    }

    /**
     * Initializes and resets recycler view
     */
    private void initializeRecyclerView() {
        RecyclerView recyclerView = view.findViewById(R.id.orderRecyclerView);
        pizzas = order.getPizzas();
        currentOrderViewAdapter = new CurrentOrderViewAdapter(getActivity().getApplicationContext(), pizzas, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(currentOrderViewAdapter);

        update();
    }

    /**
     * Called when an update is needed to order details
     */
    private void update() {
        order.setOrderNumber(storeOrder.getNextOrderNumber());
        orderNumberTextView.setText(String.format(Locale.US, "Order #%d", order.getOrderNumber()));
        subtotalTextView.setText(String.format(Locale.US, "Subtotal: $%.2f", order.getOrderPrice()));
        salesTaxTextView.setText(String.format(Locale.US, "Sales tax: $%.2f", order.getOrderTax()));
        orderTotalTextView.setText(String.format(Locale.US, "Order total: $%.2f", order.getOrderPriceTaxed()));
    }

    /**
     * When a pizza is selected
     * @param position The position of the clicked item in the RecyclerView.
     *                 Position is 0-indexed, representing the item's position in the adapter.
     */
    @Override
    public void onItemClick(int position) {
        pizzaToDelete = order.getPizzas().get(position);
    }

    /**
     * Called when user tries to remove pizza without selecting one first
     */
    private void handlePizzaNullError(String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setMessage(message).setTitle("Error!");
        alert.setPositiveButton("OK", ((dialog, which) -> { }
        ));
        alert.create().show();
    }

    /**
     * Called when remove pizza button is clicked
     */
    private void handleRemovePizza() {
        if(pizzaToDelete == null) {
            handlePizzaNullError("Select a pizza to delete first.");
            return;
        }
        order.removePizza(pizzaToDelete);
        currentOrderViewAdapter.notifyDataSetChanged();
        update();
        Toast.makeText(getContext(),"Pizza removed successfully!", Toast.LENGTH_LONG).show();
    }

    /**
     * Called when place order button is clicked
     */
    private void handlePlaceOrder() {
        if(order.getPizzas().size() == 0) {
            handlePizzaNullError("Add a pizza to order first.");
            return;
        }
        storeOrder.addOrder(order);
        Toast.makeText(getContext(),"Order added successfully!", Toast.LENGTH_LONG).show();
        orderMaker.createOrder();
        order = orderMaker.getOrder();
        pizzas = order.getPizzas();
        initializeRecyclerView();
    }

    /**
     * Handles view store orders button
     */
    private void handleStoreOrder() {
        Intent intent = new Intent(getContext(), StoreOrdersActivity.class);
        startActivity(intent);
    }
}