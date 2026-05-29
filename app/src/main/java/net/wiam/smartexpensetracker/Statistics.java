package net.wiam.smartexpensetracker;

import android.os.Bundle;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_statistics);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pieChart = findViewById(R.id.pieChart);
        switchDark = findViewById(R.id.switchDark);

        // Pie Chart Data
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(500f, "Food"));
        entries.add(new PieEntry(250f, "Transport"));
        entries.add(new PieEntry(700f, "Shopping"));
        entries.add(new PieEntry(150f, "Health"));
        PieDataSet dataSet =
                new PieDataSet(entries, "Expenses");
        PieData data =
                new PieData(dataSet);
        pieChart.setData(data);
        pieChart.getDescription().setText("Monthly Statistics");
        pieChart.setCenterText("Expenses");
        pieChart.animateY(2000);
        pieChart.invalidate();

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