package ntu.vinh.quanlychitieu.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import ntu.vinh.quanlychitieu.DAL.DatabaseHelper;
import ntu.vinh.quanlychitieu.R;

public class ManageDataActivity extends AppCompatActivity {

    private EditText etCategoryName, etTransactionId;
    private Button btnDeleteCategory, btnDeleteTransaction;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_data);

        etCategoryName = findViewById(R.id.et_category_name);
        etTransactionId = findViewById(R.id.et_transaction_id);
        btnDeleteCategory = findViewById(R.id.btn_delete_category);
        btnDeleteTransaction = findViewById(R.id.btn_delete_transaction);
        dbHelper = new DatabaseHelper(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ds_arrow_black);
        toolbar.setNavigationOnClickListener(v -> finish());

        btnDeleteCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoryName = etCategoryName.getText().toString();
                if (!categoryName.isEmpty()) {
                    dbHelper.deleteCategory(categoryName);
                    Toast.makeText(ManageDataActivity.this, "Danh mục đã được xóa", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(ManageDataActivity.this, "Vui lòng nhập tên danh mục", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDeleteTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String transactionIdStr = etTransactionId.getText().toString();
                if (!transactionIdStr.isEmpty()) {
                    int transactionId = Integer.parseInt(transactionIdStr);
                    dbHelper.deleteTransaction(transactionId);
                    Toast.makeText(ManageDataActivity.this, "Giao dịch đã được xóa", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(ManageDataActivity.this, "Vui lòng nhập ID", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}