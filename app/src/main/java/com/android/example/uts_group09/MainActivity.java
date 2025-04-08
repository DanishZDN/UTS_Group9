package com.android.example.uts_group09;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.example.uts_group09.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Check if this is a tablet layout with navigation_container present
        boolean isTablet = findViewById(R.id.navigation_container) != null;

        if (isTablet) {
            // Dual-pane layout for tablets
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.navigation_container, new HomeFragment()) // Navigation section
                    .replace(R.id.fragment_container, new MediaFragment()) // Main view
                    .commit();
        } else {
            // Single-pane layout for phones
            replaceFragment(new HomeFragment());
        }

        // Remove background to show FAB cutout properly
        binding.bottomNavigationView.setBackground(null);

        // Handle navigation item selection
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_home) {
                replaceFragment(new HomeFragment());
            } else if (itemId == R.id.navigation_calendar) {
                replaceFragment(new CalendarFragment());
            } else if (itemId == R.id.navigation_atlas) {
                replaceFragment(new AtlasFragment());
            } else if (itemId == R.id.navigation_media) {
                replaceFragment(new MediaFragment());
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}