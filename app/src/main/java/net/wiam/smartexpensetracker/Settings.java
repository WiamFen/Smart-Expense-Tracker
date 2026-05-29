package net.wiam.smartexpensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Settings extends AppCompatActivity {

    Switch switchDark;
    Button btnExport, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switchDark = findViewById(R.id.switchDark);
        btnExport = findViewById(R.id.btnExport);
        btnLogout = findViewById(R.id.btnLogout);

        switchDark.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        btnExport.setOnClickListener(v -> exportPDF());

        btnLogout.setOnClickListener(v -> {

            Intent intent = new Intent(Settings.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

            finish();
        });

    }
    private void exportPDF() {

        android.graphics.pdf.PdfDocument pdfDocument = new android.graphics.pdf.PdfDocument();
        android.graphics.Paint paint = new android.graphics.Paint();

        android.graphics.pdf.PdfDocument.PageInfo pageInfo =
                new android.graphics.pdf.PdfDocument.PageInfo.Builder(595, 842, 1).create();

        android.graphics.pdf.PdfDocument.Page page = pdfDocument.startPage(pageInfo);

        android.graphics.Canvas canvas = page.getCanvas();

        int y = 50;

        canvas.drawText("Expense Report", 200, y, paint);
        y += 50;

        for (Expense e : ExpenseData.expenseList) {

            canvas.drawText(
                    e.getCategory() + " - " + e.getAmount() + " DH - " + e.getDate(),
                    50,
                    y,
                    paint
            );
            y += 30;
        }

        pdfDocument.finishPage(page);

        String filePath = getExternalFilesDir(null) + "/expenses.pdf";

        try {
            pdfDocument.writeTo(new java.io.FileOutputStream(filePath));
            Toast.makeText(this, "PDF saved: " + filePath, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error exporting PDF", Toast.LENGTH_SHORT).show();
        }

        pdfDocument.close();
    }
}