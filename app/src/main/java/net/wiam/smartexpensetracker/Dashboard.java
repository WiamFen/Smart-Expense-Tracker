package net.wiam.smartexpensetracker;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Dashboard extends AppCompatActivity {

    TextView tvBalance;
    Button btnAddExpense;
    TextView tvUser;
    Switch switchDark;
    Button btnSettings, btnStatistics;
    RecyclerView recyclerExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Liaison XML -> Java
        tvBalance = findViewById(R.id.tvBalance);
        btnAddExpense = findViewById(R.id.btnAddExpense);
        switchDark = findViewById(R.id.switchDark);
        btnSettings = findViewById(R.id.btnSettings);
        btnStatistics = findViewById(R.id.btnStatistics);
        recyclerExpenses = findViewById(R.id.recyclerExpenses);
        tvUser = findViewById(R.id.tvUser);

        UserSession session = new UserSession(this);
        String name = session.getName();

        tvUser.setText("Hello " + name);

        // RecyclerView
        recyclerExpenses.setLayoutManager(new LinearLayoutManager(this));

        ExpenseAdapter adapter = new ExpenseAdapter(ExpenseData.expenseList);
        recyclerExpenses.setAdapter(adapter);

// update balance simple
        updateBalance();

        // Bouton Add Expense
        btnAddExpense.setOnClickListener(view -> {

            Toast.makeText(Dashboard.this,
                    "Add Expense Clicked",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Dashboard.this, AddExpense.class);
            startActivity(intent);

        });
        btnSettings.setOnClickListener(v -> {

            Intent intent = new Intent(Dashboard.this, Settings.class);
            startActivity(intent);
        });
        btnStatistics.setOnClickListener(v -> {

            Intent intent = new Intent(Dashboard.this, Statistics.class);
            startActivity(intent);
        });
        // Dark Mode Switch
        switchDark.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                Toast.makeText(Dashboard.this, "Dark Mode Enabled", Toast.LENGTH_SHORT).show();
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                Toast.makeText(Dashboard.this, "Light Mode Enabled", Toast.LENGTH_SHORT).show();
            }

        });
    }
    private void updateBalance() {

        double total = 0;

        for (Expense e : ExpenseData.expenseList) {
            total += Double.parseDouble(e.getAmount());
        }

        tvBalance.setText("Total Balance : " + total + " DH");
    }
    @Override
    protected void onResume() {
        super.onResume();

        ExpenseAdapter adapter = new ExpenseAdapter(ExpenseData.expenseList);
        recyclerExpenses.setAdapter(adapter);

        updateBalance();
    }
}