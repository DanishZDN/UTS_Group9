package com.android.example.uts_finalgroup9;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class NewJournal extends Fragment {

    private static final int REQUEST_IMAGE_PICK = 100;
    private EditText editTextJournal, tvTitle;
    private ImageView imagePreview, backToHome;
    private Button btnAddPhoto, btnSaveJournal;
    private Uri selectedImageUri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_createjournal, container, false);

        editTextJournal = view.findViewById(R.id.editTextJournal);
        tvTitle = view.findViewById(R.id.tvTitle);
        imagePreview = view.findViewById(R.id.imagePreview);
        btnAddPhoto = view.findViewById(R.id.btnAddPhoto);
        btnSaveJournal = view.findViewById(R.id.btnSaveJournal);
        backToHome = view.findViewById(R.id.backToHome);

        TextView tvDate = view.findViewById(R.id.tvDate);
        TextView tvDay = view.findViewById(R.id.tvDay);
        String currentDate = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());
        String currentDay = new SimpleDateFormat("EEEE", Locale.getDefault()).format(new Date());
        tvDate.setText("Date: " + currentDate);
        tvDay.setText("Day: " + currentDay);

        btnAddPhoto.setOnClickListener(v -> pickImage());
        btnSaveJournal.setOnClickListener(v -> saveJournal());
        backToHome.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_newJournal_to_homeFragment));

        return view;
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            imagePreview.setVisibility(View.VISIBLE);
            imagePreview.setImageURI(selectedImageUri);
        }
    }

    private void saveJournal() {
        String title = tvTitle.getText().toString();
        String content = editTextJournal.getText().toString();
        String filename = UUID.randomUUID().toString() + ".txt";
        File file = new File(requireContext().getFilesDir(), filename);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write((title + "\n" + content).getBytes());
            Toast.makeText(getContext(), "Journal saved!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Failed to save journal", Toast.LENGTH_SHORT).show();
        }

        if (selectedImageUri != null) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), selectedImageUri);
                String imageFileName = UUID.randomUUID().toString() + ".jpg";
                File imageFile = new File(requireContext().getFilesDir(), imageFileName);
                FileOutputStream out = new FileOutputStream(imageFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Navigation.findNavController(requireView()).navigate(R.id.action_newJournal_to_homeFragment);
    }
}