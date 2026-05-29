package net.wiam.smartexpensetracker;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class Settings extends AppCompatActivity {

    Switch switchDark;
    Button btnExport, btnLogout;

    ArrayList<ExpenseData> expenseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        switchDark = findViewById(R.id.switchDark);
        btnExport = findViewById(R.id.btnExport);
        btnLogout = findViewById(R.id.btnLogout);

        //GET DATA FROM INTENT (REAL DATA)
        expenseList = (ArrayList<ExpenseData>)
                getIntent().getSerializableExtra("list");

        if (expenseList == null) {
            expenseList = new ArrayList<>();
        }

        // DARK MODE
        switchDark.setOnCheckedChangeListener((buttonView, isChecked) -> {
            AppCompatDelegate.setDefaultNightMode(
                    isChecked ?
                            AppCompatDelegate.MODE_NIGHT_YES :
                            AppCompatDelegate.MODE_NIGHT_NO
            );
        });

        // EXPORT PDF
        btnExport.setOnClickListener(v -> exportPdf());

        // LOGOUT
        btnLogout.setOnClickListener(v -> {
            Toast.makeText(Settings.this, "Logged out", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }

    // PDF FUNCTION (REAL DATA)
    private void exportPdf() {
        PdfDocument pdfDocument = new PdfDocument();
        PdfDocument.PageInfo pageInfo =
                new PdfDocument.PageInfo.Builder(300, 800, 1).create();

        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        paint.setTextSize(12);
        int y = 40;
        canvas.drawText("EXPENSE REPORT", 10, y, paint);
        y += 30;
        float total = 0;
        for (ExpenseData e : expenseList) {
            canvas.drawText(
                    e.getCategory() + " - " + e.getAmount() + " - " + e.getDate(),
                    10,
                    y,
                    paint
            );
            y += 20;
            String amountOnly =
                    e.getAmount().replace("DH", "").trim();
            total += Float.parseFloat(amountOnly);
        }
        y += 30;
        canvas.drawText("TOTAL: " + total + " DH", 10, y, paint);
        pdfDocument.finishPage(page);
        try {
            String path =
                    getExternalFilesDir(null) + "/expenses_report.pdf";
            pdfDocument.writeTo(new FileOutputStream(path));
            Toast.makeText(
                    this,
                    "PDF saved: " + path,
                    Toast.LENGTH_LONG
            ).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error generating PDF", Toast.LENGTH_SHORT).show();
        }
        pdfDocument.close();
    }
}