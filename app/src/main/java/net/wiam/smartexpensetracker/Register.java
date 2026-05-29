package net.wiam.smartexpensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Register extends AppCompatActivity {

    EditText etName,etEmail,etPassword,confPass;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etName=findViewById(R.id.etName);
        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        confPass=findViewById(R.id.etConfirmPassword);
        btnRegister=findViewById(R.id.btnRegister2);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String mail = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String confpass = confPass.getText().toString().trim();

                if (TextUtils.isEmpty(mail)) {
                    etEmail.setError("Enter email");
                    etEmail.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    etPassword.setError("Enter password");
                    etPassword.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(confpass)) {
                    confPass.setError("Confirm your password");
                    confPass.requestFocus();
                    return;
                }

                if (password.length() < 6) {
                    etPassword.setError("Password must be at least 6 characters");
                    etPassword.requestFocus();
                    return;
                }

                if (!password.equals(confpass)) {
                    confPass.setError("Passwords do not match");
                    confPass.requestFocus();
                    return;
                }

                // Tout est OK ici
                Toast.makeText(Register.this, "Registration success", Toast.LENGTH_SHORT).show();


                Intent i1 = new Intent(Register.this, MainActivity.class);
                startActivity(i1);
            }
        });
    }
}