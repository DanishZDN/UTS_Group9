package com.android.example.uts_group09;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inisialisasi view
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        // Listener untuk tombol login
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Validasi input
        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Email tidak boleh kosong");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Password tidak boleh kosong");
            return;
        }

        // Di sini Anda dapat menambahkan kode untuk autentikasi pengguna
        // Misalnya menggunakan Firebase Authentication atau API sendiri

        try {
            // Contoh sederhana (ganti dengan implementasi autentikasi sesungguhnya)
            if (email.equals("admin@example.com") && password.equals("password")) {
                // Login berhasil
                Toast.makeText(LoginActivity.this, "Login berhasil", Toast.LENGTH_SHORT).show();

                Log.d(TAG, "Login successful, navigating to MainActivity");

                // Navigasi ke MainActivity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                // finish() dipanggil secara implisit karena FLAG_ACTIVITY_CLEAR_TASK
            } else {
                // Login gagal
                Toast.makeText(LoginActivity.this, "Email atau password salah", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error during login: ", e);
            Toast.makeText(LoginActivity.this, "Terjadi kesalahan: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}