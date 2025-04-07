package com.android.example.uts_finalgroup9;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class AtlasFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap gMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_atlas, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this); // Now 'this' is valid!
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        // Example: Move camera to Jakarta
        LatLng jakarta = new LatLng(-6.2088, 106.8456);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jakarta, 12f));
    }
}
