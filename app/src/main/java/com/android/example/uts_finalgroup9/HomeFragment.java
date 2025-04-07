package com.android.example.uts_finalgroup9;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class HomeFragment extends Fragment {

    private static final int REQUEST_IMAGE_PICK = 100;
    private EditText editTextJournal;
    private ImageView imagePreview;
    private Button btnAddPhoto, btnSaveJournal;
    private Uri selectedImageUri;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        editTextJournal = view.findViewById(R.id.editTextJournal);
        imagePreview = view.findViewById(R.id.imagePreview);
        btnAddPhoto = view.findViewById(R.id.btnAddPhoto);
        btnSaveJournal = view.findViewById(R.id.btnSaveJournal);

        btnAddPhoto.setOnClickListener(v -> pickImage());
        btnSaveJournal.setOnClickListener(v -> saveJournal());

        return view;
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            imagePreview.setVisibility(View.VISIBLE);
            imagePreview.setImageURI(selectedImageUri);
        }
    }

    private void saveJournal() {
        String journalText = editTextJournal.getText().toString();

        if (!journalText.isEmpty()) {
            String fileName = UUID.randomUUID().toString() + ".txt";
            File file = new File(requireContext().getFilesDir(), fileName);

            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(journalText.getBytes());
                Toast.makeText(getContext(), "Journal saved!", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Failed to save journal", Toast.LENGTH_SHORT).show();
            }
        }

        if (selectedImageUri != null) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                        requireContext().getContentResolver(), selectedImageUri);
                String imageFileName = UUID.randomUUID().toString() + ".jpg";
                File imageFile = new File(requireContext().getFilesDir(), imageFileName);
                FileOutputStream out = new FileOutputStream(imageFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.close();
                Toast.makeText(getContext(), "Image saved!", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Failed to save image", Toast.LENGTH_SHORT).show();
            }
        }

        editTextJournal.setText("");
        imagePreview.setVisibility(View.GONE);
        selectedImageUri = null;
    }
}
