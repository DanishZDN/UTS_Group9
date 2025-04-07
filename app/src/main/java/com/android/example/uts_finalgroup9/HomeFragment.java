package com.android.example.uts_finalgroup9;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ListView listViewJournals;
    private Button btnAddNewJournal;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        listViewJournals = view.findViewById(R.id.listViewJournals);
        btnAddNewJournal = view.findViewById(R.id.btnAddNewJournal);

        btnAddNewJournal.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_newJournal));

        loadJournalList();
        return view;
    }

    private void loadJournalList() {
        File dir = requireContext().getFilesDir();
        File[] files = dir.listFiles();

        List<String> journalTitles = new ArrayList<>();
        for (File file : files) {
            if (file.getName().endsWith(".txt")) {
                journalTitles.add(file.getName());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, journalTitles);
        listViewJournals.setAdapter(adapter);
    }
}
