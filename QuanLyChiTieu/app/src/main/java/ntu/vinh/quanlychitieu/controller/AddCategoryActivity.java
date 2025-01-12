package ntu.vinh.quanlychitieu.controller;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import ntu.vinh.quanlychitieu.R;
import ntu.vinh.quanlychitieu.DAL.DatabaseHelper;

public class AddCategoryActivity extends AppCompatActivity {

    private EditText etCategoryName;
    private Button btnSaveCategory;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        etCategoryName = findViewById(R.id.et_category_name);
        btnSaveCategory = findViewById(R.id.btn_save_category);
        dbHelper = new DatabaseHelper(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ds_arrow_black);
        toolbar.setNavigationOnClickListener(v -> finish());

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
            Toast.makeText(this, "Vui lòng nhập danh mục", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", categoryName);

        db.insert("categories", null, values);
        db.close();
        Toast.makeText(this, "Danh mục được thêm thành công", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}