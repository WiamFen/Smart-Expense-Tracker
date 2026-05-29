package net.wiam.smartexpensetracker;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class ExpenseDetails extends AppCompatActivity {

    TextView tvCategory, tvAmount, tvDate, tvDescription;
    Switch switchDark;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_details);

        // Liaison XML
        tvCategory = findViewById(R.id.tvCategory);
        tvAmount = findViewById(R.id.tvAmount);
        tvDate = findViewById(R.id.tvDate);
        tvDescription = findViewById(R.id.tvDescription);
        switchDark = findViewById(R.id.switchDark);
        btnDelete = findViewById(R.id.btnDelete);

        // ======================
        // GET DATA FROM INTENT
        // ======================
        String category = getIntent().getStringExtra("category");
        String amount = getIntent().getStringExtra("amount");
        String date = getIntent().getStringExtra("date");

        tvCategory.setText(category);
        tvAmount.setText(amount + " DH");
        tvDate.setText(date);
        tvDescription.setText(category);

        // ======================
        // DARK MODE
        // ======================
        switchDark.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                Toast.makeText(this, "Dark Mode Enabled", Toast.LENGTH_SHORT).show();
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                Toast.makeText(this, "Light Mode Enabled", Toast.LENGTH_SHORT).show();
            }
        });

        // ======================
        // DELETE EXPENSE
        // ======================
        btnDelete.setOnClickListener(v -> {

            for (int i = 0; i < ExpenseData.expenseList.size(); i++) {

                Expense expense = ExpenseData.expenseList.get(i);

                if (expense.getAmount().equals(amount)
                        && expense.getCategory().equals(category)
                        && expense.getDate().equals(date)) {

                    ExpenseData.expenseList.remove(i);
                    break;
                }
            }

            Toast.makeText(this, "Expense Deleted", Toast.LENGTH_SHORT).show();

            finish(); // retour Dashboard
        });
    }
}