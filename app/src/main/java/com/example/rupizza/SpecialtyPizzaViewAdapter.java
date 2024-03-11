package com.example.rupizza;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Adapter for recycler view for specialty pizzas
 *
 * @author Frances Cortuna
 */
public class SpecialtyPizzaViewAdapter extends RecyclerView.Adapter<SpecialtyPizzaViewHolder> {
    Context context;
    List<SpecialtyPizzas> specialtyPizzas;
    int row_index = -1;
    private final RecyclerViewInterface recyclerViewInterface;

    /**
     * Constructor for the adapter.
     *
     * @param context The context in which the adapter is created.
     * @param specialtyPizzas The list of specialty pizzas to be displayed.
     */
    public SpecialtyPizzaViewAdapter(Context context, List<SpecialtyPizzas> specialtyPizzas, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.specialtyPizzas = specialtyPizzas;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    /**
     * Creates a new {@link SpecialtyPizzaViewHolder} by inflating the layout.
     *
     * @param parent The ViewGroup into which the new View will be added.
     * @param viewType The type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public SpecialtyPizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SpecialtyPizzaViewHolder(LayoutInflater.from(context).inflate(R.layout.specialty_pizza_card, parent, false));
    }

    /**
     * Binds data to the views inside the ViewHolder.
     *
     * @param holder The ViewHolder to bind data to.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull SpecialtyPizzaViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nameView.setText(specialtyPizzas.get(position).getPizzaName());
        holder.imageView.setImageResource(specialtyPizzas.get(position).getImage());
        holder.toppingsView.setText(specialtyPizzas.get(position).getToppingsSauce());

        holder.relativeLayout.setOnClickListener(v -> {
            row_index=position;
            notifyDataSetChanged();

            if(recyclerViewInterface != null) {
                recyclerViewInterface.onItemClick(position);
            }
        });

        if(row_index == position) {
            holder.relativeLayout.setBackgroundColor(Color.parseColor("#BF92E4"));
        } else {
            holder.relativeLayout.setBackgroundColor(Color.WHITE);
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in the adapter.
     */
    @Override
    public int getItemCount() {
        return specialtyPizzas.size();
    }
}
