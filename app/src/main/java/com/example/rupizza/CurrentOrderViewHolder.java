package com.example.rupizza;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * ViewHolder for displaying list of pizzas in current order in a RecyclerView.
 *
 * @author Frances Cortuna
 */
public class CurrentOrderViewHolder extends RecyclerView.ViewHolder {
    TextView sizeTypeTextView, toppingsTextView, sauceTextView, extraCheeseTextView, priceTextView;
    RelativeLayout relativeLayout;
    RecyclerViewInterface recyclerViewInterface;

    /**
     * Constructor for the ViewHolder.
     *
     * @param itemView The view representing an item in the RecyclerView.
     */
    public CurrentOrderViewHolder(@NonNull View itemView) {
        super(itemView);
        sizeTypeTextView = itemView.findViewById(R.id.sizeTypeTextView);
        toppingsTextView = itemView.findViewById(R.id.toppingsTextView);
        sauceTextView = itemView.findViewById(R.id.sauceTextView);
        extraCheeseTextView = itemView.findViewById(R.id.extraCheeseTextView);
        priceTextView = itemView.findViewById(R.id.priceTextView);
        recyclerViewInterface = itemView.findViewById(R.id.orderRecyclerView);
        relativeLayout = itemView.findViewById(R.id.currentOrderRelativeLayout);

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
