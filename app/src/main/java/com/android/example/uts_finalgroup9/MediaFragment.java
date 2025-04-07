package com.android.example.uts_finalgroup9;

import android.os.Bundle;
import android.view.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MediaFragment extends Fragment {

    private RecyclerView recyclerView;
    private MediaAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media, container, false);
        recyclerView = view.findViewById(R.id.recyclerMedia);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        loadImages();
        return view;
    }

    private void loadImages() {
        File dir = requireContext().getFilesDir();
        File[] files = dir.listFiles((file, name) -> name.endsWith(".jpg"));
        List<File> imageFiles = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                imageFiles.add(file);
            }
        }
        adapter = new MediaAdapter(imageFiles);
        recyclerView.setAdapter(adapter);
    }
}
