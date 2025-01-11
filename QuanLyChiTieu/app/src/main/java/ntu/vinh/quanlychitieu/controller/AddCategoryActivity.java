package ntu.vinh.quanlychitieu.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ntu.vinh.quanlychitieu.BLL.TransactionRepository;
import ntu.vinh.quanlychitieu.R;

public class AddCategoryActivity extends AppCompatActivity {
    private EditText etCategoryName;
    private Button btnSaveCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        // Ánh xạ view
        etCategoryName = findViewById(R.id.et_category_name);
        btnSaveCategory = findViewById(R.id.btn_save_category);

        // Xử lý lưu danh mục
        btnSaveCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCategory();
            }
        });
    }

    private void saveCategory() {
        String categoryName = etCategoryName.getText().toString();

        if (categoryName.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên danh mục", Toast.LENGTH_SHORT).show();
            return;
        }

        TransactionRepository repository = new TransactionRepository(this);
        repository.addCategory(categoryName);

        Toast.makeText(this, "Danh mục đã được thêm", Toast.LENGTH_SHORT).show();
        finish(); // Quay lại màn hình trước đó
    }
}