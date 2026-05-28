package net.wiam.smartexpensetracker;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin2;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Liaison XML -> Java
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin2 = findViewById(R.id.btnLogin2);
        textView2 = findViewById(R.id.textView2);

        // Action bouton Login
        btnLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(Login.this,
                            "Please fill all fields",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Login.this,
                            "Login Success",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Action TextView Register
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Login.this,
                        "Go to Register Page",
                        Toast.LENGTH_SHORT).show();

                // Intent intent = new Intent(Login.this, Register.class);
                // startActivity(intent);
            }
        });

    }
}