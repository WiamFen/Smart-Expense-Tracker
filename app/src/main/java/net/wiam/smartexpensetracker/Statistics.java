package net.wiam.smartexpensetracker;

import android.os.Bundle;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import com.github.mikephil.charting.data.Entry;

import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class Statistics extends AppCompatActivity {

    PieChart pieChart;
    LineChart lineChart;
    BarChart barChart;
    BarChart histogramChart;
    Switch switchDark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        pieChart = findViewById(R.id.pieChart);
        lineChart = findViewById(R.id.lineChart);
        barChart = findViewById(R.id.barChart);
        histogramChart = findViewById(R.id.histogramChart);
        switchDark = findViewById(R.id.switchDark);

        switchDark.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO);
            }

        });

        loadPieChart();
        loadLineChart();
        loadBarChart();
        loadHistogram();
    }

    private void loadPieChart() {

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
        pieChart.setCenterText("Expenses");
        pieChart.getDescription()
                .setText("Distribution by category");
        pieChart.animateY(1500);
        pieChart.invalidate();
    }

    private void loadLineChart() {

        ArrayList<Entry> entries = new ArrayList<>();

        entries.add(new Entry(1, 100));
        entries.add(new Entry(2, 250));
        entries.add(new Entry(3, 300));
        entries.add(new Entry(4, 450));
        entries.add(new Entry(5, 600));
        entries.add(new Entry(6, 750));
        entries.add(new Entry(7, 900));

        LineDataSet dataSet =
                new LineDataSet(entries,
                        "Monthly evolution");

        LineData data =
                new LineData(dataSet);

        lineChart.setData(data);
        lineChart.getDescription()
                .setText("Cumulative monthly expenses");
        lineChart.animateX(1500);
        lineChart.invalidate();
    }

    private void loadBarChart() {

        ArrayList<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(1, 80));
        entries.add(new BarEntry(2, 120));
        entries.add(new BarEntry(3, 60));
        entries.add(new BarEntry(4, 200));
        entries.add(new BarEntry(5, 100));
        entries.add(new BarEntry(6, 50));
        entries.add(new BarEntry(7, 170));

        BarDataSet dataSet =
                new BarDataSet(entries,
                        "Last 7 days");

        BarData data =
                new BarData(dataSet);

        barChart.setData(data);
        barChart.getDescription()
                .setText("Expenses of last 7 days");
        barChart.animateY(1500);
        barChart.invalidate();
    }

    private void loadHistogram() {

        ArrayList<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(1, 500));
        entries.add(new BarEntry(2, 250));
        entries.add(new BarEntry(3, 700));
        entries.add(new BarEntry(4, 150));

        BarDataSet dataSet =
                new BarDataSet(entries,
                        "Categories");

        BarData data =
                new BarData(dataSet);

        histogramChart.setData(data);
        histogramChart.getDescription()
                .setText("Comparison by category");
        histogramChart.animateY(1500);
        histogramChart.invalidate();
    }
}