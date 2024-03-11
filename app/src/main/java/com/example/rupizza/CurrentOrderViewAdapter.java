package com.example.rupizza;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import Pizzeria.Pizza;
import Pizzeria.Topping;

/**
 * Adapter for recycler view for list of pizzas in current order
 *
 * @author Frances Cortuna
 */
public class CurrentOrderViewAdapter extends RecyclerView.Adapter<CurrentOrderViewHolder> {
    Context context;
    ArrayList<Pizza> pizzas;
    int row_index = -1;
    private final RecyclerViewInterface recyclerViewInterface;

    /**
     * Constructor for adapter
     * @param context Context adapter is created in
     * @param pizzas List of pizzas to show
     * @param recyclerViewInterface Interface for recycler view
     */
    public CurrentOrderViewAdapter(Context context, ArrayList<Pizza> pizzas, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.pizzas = pizzas;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    /**
     * Creates a new {@link CurrentOrderViewHolder} by inflating the layout.
     *
     * @param parent The ViewGroup into which the new View will be added.
     * @param viewType The type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public CurrentOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CurrentOrderViewHolder(LayoutInflater.from(context).inflate(R.layout.current_order_card, parent, false));
    }

    /**
     * Binds data to the views inside ViewHolder
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull CurrentOrderViewHolder holder, int position) {
        Pizza pizza = pizzas.get(position);
        String sizeString = pizza.getSize().toString().substring(0, 1).toUpperCase() + pizza.getSize().toString().substring(1).toLowerCase();
        String sizeType = String.format("%s %s", sizeString, getPizzaTypeString(pizza));
        holder.sizeTypeTextView.setText(sizeType);
        holder.toppingsTextView.setText(getToppingsString(pizza));
        holder.sauceTextView.setText(String.format("Sauce: %s", pizza.isExtraSauce() ? "Extra " + pizza.getSauce().getSauceName() : pizza.getSauce().getSauceName()));
        holder.extraCheeseTextView.setText(String.format("%s", pizza.isExtraCheese() ? "Extra cheese" : "No extra cheese"));
        holder.priceTextView.setText(String.format(Locale.US, "Price: $%.2f", pizza.price()));

        holder.relativeLayout.setOnClickListener(v -> {
            int adapterPosition = holder.getBindingAdapterPosition();
            row_index=adapterPosition;
            notifyDataSetChanged();

            if(recyclerViewInterface != null) {
                recyclerViewInterface.onItemClick(adapterPosition);
            }
        });

        if(row_index == position) {
            holder.relativeLayout.setBackgroundColor(Color.parseColor("#BF92E4"));
        } else {
            holder.relativeLayout.setBackgroundColor(Color.WHITE);
        }
    }

    /**
     * Builds string to get name of pizza type
     * @param pizza Pizza to get the name of
     * @return StringBuilder with correct formatting of name string
     */
    private StringBuilder getPizzaTypeString(Pizza pizza) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < pizza.getClass().getSimpleName().length(); i++) {
            char currentChar = pizza.getClass().getSimpleName().charAt(i);

            if (i > 0 && Character.isUpperCase(currentChar)) {
                result.append(" ");
            }

            result.append(currentChar);
        }

        return result;
    }

    /**
     * Builds string to list the toppings
     * @param pizza Pizza to get toppings from
     * @return StringBuilder with correct formatting of toppings
     */
    private StringBuilder getToppingsString(Pizza pizza) {
        StringBuilder toppingsString = new StringBuilder();
        for(Topping topping : pizza.getToppings()) {
            toppingsString.append(topping.getToppingName()).append(", ");
        }
        if (toppingsString.length() > 0) {
            toppingsString.setLength(toppingsString.length() - 2);
        }

        return toppingsString;
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in the adapter.
     */
    @Override
    public int getItemCount() {
        return pizzas.size();
    }
}
