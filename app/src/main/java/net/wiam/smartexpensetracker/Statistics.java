package net.wiam.smartexpensetracker;

import android.os.Bundle;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class Statistics extends AppCompatActivity {

    PieChart pieChart;
    Switch switchDark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        pieChart = findViewById(R.id.pieChart);
        switchDark = findViewById(R.id.switchDark);

        // DARK MODE
        switchDark.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        loadChart();
    }

    private void loadChart() {

        ArrayList<PieEntry> entries = new ArrayList<>();

        double total = 0;

        for (Expense e : ExpenseData.expenseList) {
            total += Double.parseDouble(e.getAmount());
        }

        entries.add(new PieEntry((float) total, "Expenses"));

        PieDataSet dataSet = new PieDataSet(entries, "Expenses");
        PieData data = new PieData(dataSet);

        pieChart.setData(data);
        pieChart.invalidate();
    }
}