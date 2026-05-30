package net.wiam.smartexpensetracker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.Calendar;

public class AddExpense extends AppCompatActivity {

    Switch switchDark;
    EditText etAmount, etDescription;
    Spinner spCategory;
    Button btnDate, btnSave, btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        // Liaison XML
        switchDark = findViewById(R.id.switchDark);
        etAmount = findViewById(R.id.etAmount);
        etDescription = findViewById(R.id.etDescription);
        spCategory = findViewById(R.id.spCategory);
        btnDate = findViewById(R.id.btnDate);
        btnSave = findViewById(R.id.btnSave);

        btnHome = findViewById(R.id.btnHome);

        btnHome.setOnClickListener(v -> {

            Intent intent =
                    new Intent(AddExpense.this,
                            Dashboard.class);

            startActivity(intent);

            finish();
        });

        // =====================
        // SPINNER CATEGORY
        // =====================
        String[] categories = {"Food", "Transport", "Shopping", "Bills", "Other"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                categories
        );

        spCategory.setAdapter(adapter);

        // =====================
        // DARK MODE
        // =====================
        switchDark.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                Toast.makeText(this, "Dark Mode Enabled", Toast.LENGTH_SHORT).show();
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                Toast.makeText(this, "Light Mode Enabled", Toast.LENGTH_SHORT).show();
            }
        });

        // =====================
        // DATE PICKER
        // =====================
        btnDate.setOnClickListener(view -> {

            Calendar calendar = Calendar.getInstance();

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddExpense.this,
                    (DatePicker view1, int selectedYear, int selectedMonth, int selectedDay) -> {

                        String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        btnDate.setText(date);

                    },
                    year, month, day
            );

            datePickerDialog.show();
        });

        // =====================
        // SAVE BUTTON
        // =====================
        btnSave.setOnClickListener(view -> {

            String amount = etAmount.getText().toString();
            String category = spCategory.getSelectedItem().toString();
            String date = btnDate.getText().toString();

            if (amount.isEmpty()) {
                Toast.makeText(this, "Enter amount", Toast.LENGTH_SHORT).show();
            } else {

                // 1. Ajouter dans "database"
                ExpenseData.expenseList.add(
                        new Expense(category, amount, date)
                );

                Toast.makeText(this, "Expense Saved", Toast.LENGTH_SHORT).show();

                // 2. Retour Dashboard
                Intent intent = new Intent(AddExpense.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });
    }
}