package ntu.vinh.quanlychitieu.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import ntu.vinh.quanlychitieu.R;
import ntu.vinh.quanlychitieu.BLL.TransactionRepository;
import ntu.vinh.quanlychitieu.MODELS.Transaction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsActivity extends AppCompatActivity {

    private Spinner spinnerCategory;
    private PieChart pieChart;
    private TransactionRepository transactionRepository;
    private TextView tvSelectedMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        tvSelectedMonth = findViewById(R.id.tvSelectedMonth);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        pieChart = findViewById(R.id.pieChart);
        transactionRepository = new TransactionRepository(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ds_arrow_black);
        toolbar.setNavigationOnClickListener(v -> finish());

        setupSpinners();
        setupMonthYearPicker();
    }

    private void setupSpinners() {
        List<String> categories = getCategories();

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = (String) parent.getItemAtPosition(position);
                displayCategoryStats(selectedCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setupMonthYearPicker() {
        tvSelectedMonth.setOnClickListener(v -> {
            MonthYearPickerDialog dialog = new MonthYearPickerDialog();
            dialog.setListener((view, year, month, dayOfMonth) -> {
                String selectedMonthYear = String.format("%04d-%02d", year, month + 1);
                tvSelectedMonth.setText(selectedMonthYear);
                displayMonthlyStats(selectedMonthYear);
            });
            dialog.show(getSupportFragmentManager(), "MonthYearPickerDialog");
        });
    }

    private List<String> getCategories() {
        List<String> categories = transactionRepository.getAllCategories();
        categories.add(0, "Tất cả các danh mục"); // Add "All" option at the beginning
        return categories;
    }

    private void displayMonthlyStats(String monthYear) {
        List<Transaction> transactions = transactionRepository.getAllTransactions();
        Map<String, Integer> monthlyStats = new HashMap<>();

        for (Transaction transaction : transactions) {
            if (transaction.getDate().startsWith(monthYear)) {
                String category = transaction.getCategory();
                int amount = Integer.parseInt(transaction.getAmount());
                monthlyStats.put(category, monthlyStats.getOrDefault(category, 0) + amount);
            }
        }

        if (monthlyStats.isEmpty()) {
            // Log or handle the case where no data is found for the selected month
            System.out.println("No data found for the selected month: " + monthYear);
        }

        List<PieEntry> entries = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : monthlyStats.entrySet()) {
            entries.add(new PieEntry(entry.getValue(), entry.getKey()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Monthly Stats");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);
        pieChart.invalidate(); // refresh
    }

    private void displayCategoryStats(String category) {
        List<Transaction> transactions = transactionRepository.getAllTransactions();
        Map<String, Integer> categoryStats = new HashMap<>();

        for (Transaction transaction : transactions) {
            if (category.equals("Tất cả các danh mục") || transaction.getCategory().equals(category)) {
                String month = transaction.getDate().substring(0, 7); // Assuming date format is YYYY-MM-DD
                int amount = Integer.parseInt(transaction.getAmount());
                categoryStats.put(month, categoryStats.getOrDefault(month, 0) + amount);
            }
        }

        List<PieEntry> entries = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : categoryStats.entrySet()) {
            entries.add(new PieEntry(entry.getValue(), entry.getKey()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Category Stats");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);
        pieChart.invalidate(); // refresh
    }
}