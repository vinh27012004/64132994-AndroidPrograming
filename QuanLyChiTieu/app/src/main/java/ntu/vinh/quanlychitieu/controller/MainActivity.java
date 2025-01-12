package ntu.vinh.quanlychitieu.controller;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import ntu.vinh.quanlychitieu.R;
import ntu.vinh.quanlychitieu.DAL.DatabaseHelper;
import ntu.vinh.quanlychitieu.MODELS.Transaction;
import ntu.vinh.quanlychitieu.BLL.TransactionRepository;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD_TRANSACTION = 1;
    private static final int REQUEST_CODE_MANAGE_DATA = 2;
    private RecyclerView rvRecentTransactions;
    private TransactionAdapter transactionAdapter;
    private DatabaseHelper dbHelper;
    private TextView tvBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvRecentTransactions = findViewById(R.id.rv_recent_transactions);
        rvRecentTransactions.setLayoutManager(new LinearLayoutManager(this));

        tvBalance = findViewById(R.id.tv_balance);
        dbHelper = new DatabaseHelper(this);
        loadTransactions();
        updateBalance();

        FloatingActionButton fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(view -> showPopupMenu(view));
    }

    private void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_add_options, popup.getMenu());
        popup.setOnMenuItemClickListener(this::onMenuItemClick);
        popup.show();
    }

    private boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_add_transaction) {
            startActivityForResult(new Intent(this, AddTransactionActivity.class), REQUEST_CODE_ADD_TRANSACTION);
            return true;
        } else if (id == R.id.menu_add_category) {
            startActivity(new Intent(this, AddCategoryActivity.class));
            return true;
        } else if (id == R.id.menu_manage_data) {
            startActivityForResult(new Intent(this, ManageDataActivity.class), REQUEST_CODE_MANAGE_DATA);
            return true;
        } else if (id == R.id.menu_statistics) {
            startActivity(new Intent(this, StatisticsActivity.class));
            return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_TRANSACTION && resultCode == RESULT_OK) {
            loadTransactions();
            updateBalance();
        } else if (requestCode == REQUEST_CODE_MANAGE_DATA && resultCode == RESULT_OK) {
            loadTransactions();
            updateBalance();
        }
    }

    private void loadTransactions() {
        List<Transaction> transactionList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("transactions", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex("id");
            int amountIndex = cursor.getColumnIndex("amount");
            int categoryIndex = cursor.getColumnIndex("category");
            int noteIndex = cursor.getColumnIndex("note");
            int dateIndex = cursor.getColumnIndex("date");

            do {
                int id = idIndex != -1 ? cursor.getInt(idIndex) : 0;
                String amount = amountIndex != -1 ? cursor.getString(amountIndex) : "";
                String category = categoryIndex != -1 ? cursor.getString(categoryIndex) : "";
                String note = noteIndex != -1 ? cursor.getString(noteIndex) : "";
                String date = dateIndex != -1 ? cursor.getString(dateIndex) : "";
                transactionList.add(new Transaction(id, amount, category, note, date));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        transactionAdapter = new TransactionAdapter(transactionList);
        rvRecentTransactions.setAdapter(transactionAdapter);
    }

    private void updateBalance() {
        List<Transaction> transactions = new TransactionRepository(this).getAllTransactions();
        int balance = 0;

        for (Transaction transaction : transactions) {
            if ("Thêm số dư".equals(transaction.getCategory())) {
                balance += Integer.parseInt(transaction.getAmount());
            } else {
                balance -= Integer.parseInt(transaction.getAmount());
            }
        }

        tvBalance.setText(String.format("%d VNĐ", balance));

        if (balance < 0) {
            tvBalance.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        } else if (balance == 0) {
            tvBalance.setTextColor(getResources().getColor(android.R.color.black));
        } else {
            tvBalance.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        }
    }
}