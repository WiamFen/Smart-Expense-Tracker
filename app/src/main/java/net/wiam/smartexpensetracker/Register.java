package net.wiam.smartexpensetracker;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    EditText etName, etEmail, etPassword, etConfirmPassword;
    Button btnRegister2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Liaison XML -> Java
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister2 = findViewById(R.id.btnRegister2);

        // Action bouton Register
        btnRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String confirmPassword = etConfirmPassword.getText().toString().trim();

                // Vérification des champs
                if(name.isEmpty() || email.isEmpty()
                        || password.isEmpty() || confirmPassword.isEmpty()){

                    Toast.makeText(Register.this,
                            "Please fill all fields",
                            Toast.LENGTH_SHORT).show();
                }

                // Vérification mot de passe
                else if(!password.equals(confirmPassword)){

                    Toast.makeText(Register.this,
                            "Passwords do not match",
                            Toast.LENGTH_SHORT).show();
                }

                else{

                    Toast.makeText(Register.this,
                            "Registration Success",
                            Toast.LENGTH_SHORT).show();

                    // Ici tu peux ajouter:
                    // Firebase, SQLite, MySQL API, etc.
                }
            }
        });

    }
}