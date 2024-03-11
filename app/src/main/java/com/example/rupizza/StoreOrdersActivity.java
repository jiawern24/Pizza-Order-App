package com.example.rupizza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;

import Pizzeria.Order;
import Pizzeria.Pizza;
import Pizzeria.StoreOrder;

/**
 * Activity to handle Store Orders
 *
 * @author Frances Cortuna
 */
public class StoreOrdersActivity extends AppCompatActivity implements RecyclerViewInterface {
    RecyclerView recyclerView;
    ArrayList<Pizza> pizzas;
    ArrayList<Order> orders;
    Order order;
    StoreOrder storeOrder = StoreOrder.getInstance();
    Spinner orderSpinner;
    FloatingActionButton backBtn;
    TextView orderTotalTextView;
    Button removeOrderBtn;

    /**
     * Called when Activity is created
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        backBtn = findViewById(R.id.backButton);
        orderSpinner = findViewById(R.id.orderSpinner);
        recyclerView = findViewById(R.id.orderRecyclerView);
        orderTotalTextView = findViewById(R.id.orderTotalTextView);
        removeOrderBtn = findViewById(R.id.cancelOrderButton);
        initializeOrderSpinner();
        if(storeOrder.getOrders().size() != 0) pizzas = storeOrder.getOrders().get(0).getPizzas();
        else pizzas = new ArrayList<>();
        initializeRecyclerView();
        backBtn.setOnClickListener(v -> finish());
        removeOrderBtn.setOnClickListener(v -> handleRemoveOrder());
    }

    /**
     * Initializes and resets order spinner
     */
    private void initializeOrderSpinner() {
        orders = storeOrder.getOrders();
        orderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            /**
             * When an item from spinner is selected
             * @param parent The AdapterView where the selection happened
             * @param view The view within the AdapterView that was clicked
             * @param position The position of the view in the adapter
             * @param id The row id of the item that is selected
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                order = (Order) parent.getItemAtPosition(position);
                pizzas = order.getPizzas();
                if(order != null) orderTotalTextView.setText(String.format(Locale.US, "Order total: $%.2f", order.getOrderPriceTaxed()));
                else orderTotalTextView.setText(R.string.order_total);
                initializeRecyclerView();
            }

            /**
             * When nothing is selected (nothing happens)
             * @param parent The AdapterView that now contains no selected item.
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<Order> orderListAdapter = new ArrayAdapter<>(this, R.layout.order_spinner_item, R.id.orderNumberTextView, orders);
        orderSpinner.setAdapter(orderListAdapter);
    }

    /**
     * Initializes and resets recycler view
     */
    private void initializeRecyclerView() {
        CurrentOrderViewAdapter currentOrderViewAdapter = new CurrentOrderViewAdapter(getApplicationContext(), pizzas, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(currentOrderViewAdapter);
    }

    /**
     * When item is clicked (nothing happens)
     * @param position The position of the clicked item in the RecyclerView.
     *                 Position is 0-indexed, representing the item's position in the adapter.
     */
    @Override
    public void onItemClick(int position) {

    }

    /**
     * Called when remove order button is clicked
     */
    private void handleRemoveOrder() {
        if(order == null || orders.size() == 0) {
            handlePizzaNullError();
            return;
        }
        storeOrder.cancelOrder(order);
        initializeOrderSpinner();
        if(storeOrder.getOrders().size() != 0) pizzas = storeOrder.getOrders().get(0).getPizzas();
        else {
            pizzas = new ArrayList<>();
            orderTotalTextView.setText(R.string.order_total);
        }
        initializeRecyclerView();
        Toast.makeText(getApplicationContext(),"Order removed successfully!", Toast.LENGTH_LONG).show();
    }

    /**
     * Called when user tries to remove pizza without selecting one first
     */
    private void handlePizzaNullError() {
        AlertDialog.Builder alert = new AlertDialog.Builder(StoreOrdersActivity.this);
        alert.setMessage("No order to remove").setTitle("Error!");
        alert.setPositiveButton("OK", ((dialog, which) -> { }
        ));
        alert.create().show();
    }
}