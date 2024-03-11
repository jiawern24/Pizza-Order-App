package com.example.rupizza;

/**
 * Interface for handling item click events in a RecyclerView.
 * Implement this interface to define behavior when an item in the RecyclerView is clicked.
 *
 * @author Frances Cortuna
 */
public interface RecyclerViewInterface {

    /**
     * Called when an item in the RecyclerView is clicked.
     *
     * @param position The position of the clicked item in the RecyclerView.
     *                 Position is 0-indexed, representing the item's position in the adapter.
     */
    void onItemClick(int position);
}
