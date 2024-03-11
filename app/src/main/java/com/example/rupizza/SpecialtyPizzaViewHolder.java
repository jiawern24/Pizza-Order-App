package com.example.rupizza;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * ViewHolder for displaying specialty pizzas in a RecyclerView.
 *
 * @author Frances Cortuna
 */
public class SpecialtyPizzaViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView nameView;
    TextView toppingsView;
    RelativeLayout relativeLayout;
    RecyclerViewInterface recyclerViewInterface;

    /**
     * Constructor for the ViewHolder.
     *
     * @param itemView The view representing an item in the RecyclerView.
     */
    public SpecialtyPizzaViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        nameView = itemView.findViewById(R.id.pizzaType);
        toppingsView = itemView.findViewById(R.id.pizzaToppings);
        relativeLayout = itemView.findViewById(R.id.relativeLayout);
        recyclerViewInterface = itemView.findViewById(R.id.recyclerView);

        itemView.setOnClickListener(v -> {
            if(recyclerViewInterface != null) {
                int position = getBindingAdapterPosition();

                if(position != RecyclerView.NO_POSITION) {
                    recyclerViewInterface.onItemClick(position);
                }
            }
        });
    }
}
