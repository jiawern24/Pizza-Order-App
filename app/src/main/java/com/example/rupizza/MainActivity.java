package com.example.rupizza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.rupizza.databinding.ActivityMainBinding;

/**
 * Main activity page, which is the page where user can order either specialty pizza or a BYO pizza
 *
 * @author Frances Cortuna
 */
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    /**
     * Method is called when activity is being created, sets up bottom navigation fragment and sets order as default fragment
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new PizzaFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.order) replaceFragment(new PizzaFragment());
            else if(item.getItemId() == R.id.currentOrder) replaceFragment(new CurrentOrderFragment());
            return true;
        });
    }

    /**
     * When a different tab on the bottom is clicked, method is called to replace the fragment to appropriate one
     * @param fragment Fragment to replace
     */
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}