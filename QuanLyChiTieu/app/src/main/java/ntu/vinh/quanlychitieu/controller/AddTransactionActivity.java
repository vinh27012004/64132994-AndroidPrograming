package ntu.vinh.quanlychitieu.controller;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.DatePickerDialog;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.List;

import ntu.vinh.quanlychitieu.BLL.TransactionRepository;
import ntu.vinh.quanlychitieu.R;

public class AddTransactionActivity extends AppCompatActivity {
    private EditText etAmount, etNote;
    private Spinner spCategory;
    private TextView tvDate;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        // Ánh xạ view
        etAmount = findViewById(R.id.et_amount);
        etNote = findViewById(R.id.et_note);
        spCategory = findViewById(R.id.sp_category);
        tvDate = findViewById(R.id.tv_date);
        btnSave = findViewById(R.id.btn_save_transaction);

        // Load categories into spinner
        loadCategories();

        // Xử lý chọn ngày
        tvDate.setOnClickListener(v -> showDatePickerDialog());

        // Xử lý lưu giao dịch
        btnSave.setOnClickListener(v -> saveTransaction());
    }

    private void loadCategories() {
        TransactionRepository repository = new TransactionRepository(this);
        List<String> categories = repository.getAllCategories();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapter);
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            String selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
            tvDate.setText(selectedDate);
        }, year, month, day).show();
    }

    private void saveTransaction() {
        String amountStr = etAmount.getText().toString();
        String category = spCategory.getSelectedItem().toString();
        String note = etNote.getText().toString();
        String date = tvDate.getText().toString();

        if (amountStr.isEmpty() || date.equals("Chọn ngày")) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountStr);

        TransactionRepository repository = new TransactionRepository(this);
        repository.addTransaction(amount, category, note, date);

        Toast.makeText(this, "Giao dịch đã được lưu", Toast.LENGTH_SHORT).show();
        finish(); // Đóng Activity sau khi lưu
    }
}