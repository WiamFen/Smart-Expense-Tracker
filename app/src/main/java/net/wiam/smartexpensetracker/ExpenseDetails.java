package net.wiam.smartexpensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ExpenseDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView tvCategory, tvAmount, tvDate, tvDescription;
        Button btnDelete;
        Switch switchDark;

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_expense_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvCategory = findViewById(R.id.tvCategory);
        tvAmount = findViewById(R.id.tvAmount);
        tvDate = findViewById(R.id.tvDate);
        tvDescription = findViewById(R.id.tvDescription);
        btnDelete = findViewById(R.id.btnDelete);
        switchDark = findViewById(R.id.switchDark);

        // Get data from intent
        Intent intent = getIntent();
        String category =
                intent.getStringExtra("category");
        String amount =
                intent.getStringExtra("amount");
        String date =
                intent.getStringExtra("date");
        String description =
                intent.getStringExtra("description");

        // Set data
        tvCategory.setText(category);
        tvAmount.setText(amount);
        tvDate.setText(date);
        tvDescription.setText(description);

        // Delete button
        btnDelete.setOnClickListener(v -> {
            Toast.makeText(
                    ExpenseDetails.this,
                    "Expense Deleted",
                    Toast.LENGTH_SHORT
            ).show();
            Intent dashboardIntent =
                    new Intent(
                            ExpenseDetails.this,
                            Dashboard.class
                    );
            startActivity(dashboardIntent);
            finish();
        });

        // Dark Mode
        switchDark.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {

                    if(isChecked) {
                        AppCompatDelegate.setDefaultNightMode(
                                AppCompatDelegate.MODE_NIGHT_YES
                        );
                    }
                    else {
                        AppCompatDelegate.setDefaultNightMode(
                                AppCompatDelegate.MODE_NIGHT_NO
                        );
                    }
                });
    }
}