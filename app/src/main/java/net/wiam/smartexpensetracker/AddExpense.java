package net.wiam.smartexpensetracker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class AddExpense extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText etAmount, etDescription;

        Spinner spCategory;

        Button btnDate, btnSave;

        Switch switchDark;

        String selectedDate = "";

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_expense);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etAmount = findViewById(R.id.etAmount);

        etDescription = findViewById(R.id.etDescription);

        spCategory = findViewById(R.id.spCategory);

        btnDate = findViewById(R.id.btnDate);

        btnSave = findViewById(R.id.btnSave);

        switchDark = findViewById(R.id.switchDark);

        // Spinner categories

        String[] categories = {
                "Food",
                "Transport",
                "Shopping",
                "Bills",
                "Health",
                "Other"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        categories
                );

        spCategory.setAdapter(adapter);

        // Date Picker

        btnDate.setOnClickListener(v -> {

            Calendar calendar = Calendar.getInstance();

            int year = calendar.get(Calendar.YEAR);

            int month = calendar.get(Calendar.MONTH);

            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog =
                    new DatePickerDialog(
                            AddExpense.this,

                            (view, year1, month1, dayOfMonth) -> {

                                String date =
                                        dayOfMonth + "/"
                                                + (month1 + 1)
                                                + "/"
                                                + year1;

                                btnDate.setText(date);

                            },

                            year,
                            month,
                            day
                    );

            datePickerDialog.show();

        });

        // Save Expense

        btnSave.setOnClickListener(v -> {

            String amount =
                    etAmount.getText().toString();

            String description =
                    etDescription.getText().toString();

            String category =
                    spCategory.getSelectedItem().toString();

            if(amount.isEmpty()) {

                etAmount.setError("Enter amount");

                return;
            }

            Toast.makeText(
                    AddExpense.this,
                    "Expense Saved",
                    Toast.LENGTH_SHORT
            ).show();

            Intent intent =
                    new Intent(
                            AddExpense.this,
                            Dashboard.class
                    );

            startActivity(intent);

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