package com.android.example.uts_finalgroup9;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.android.example.uts_finalgroup9.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.appbar.MaterialToolbar;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Set default fragment
        replaceFragment(new HomeFragment());

        // Remove background to show FAB cutout properly
        binding.bottomNavigationView.setBackground(null);

        // Handle navigation item selection
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_home) {
                replaceFragment(new HomeFragment());
                binding.topAppBar.setTitle("Journee");
            } else if (itemId == R.id.navigation_calendar) {
                replaceFragment(new CalendarFragment());
                binding.topAppBar.setTitle("Calendar");
            } else if (itemId == R.id.navigation_atlas) {
                replaceFragment(new AtlasFragment());
                binding.topAppBar.setTitle("Atlas");
            } else if (itemId == R.id.navigation_media) {
                replaceFragment(new MediaFragment());
                binding.topAppBar.setTitle("Media");
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
