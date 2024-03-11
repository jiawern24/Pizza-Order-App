package com.example.rupizza;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * Adapter for managing the fragments in a ViewPager2.
 *
 * @author Frances Cortuna
 */
public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    /**
     * Creates a new fragment instance based on the given position.
     *
     * @param position The position of the fragment.
     * @return A new Fragment instance.
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position) {
            case 0: return new SpecialtyPizzaFragment();
            case 1: return new ByoPizzaFragment();
            default: return new SpecialtyPizzaFragment();
        }
    }

    /**
     * Returns the total number of fragments managed by the adapter.
     *
     * @return The total number of fragments.
     */
    @Override
    public int getItemCount() {
        return 2;
    }
}
