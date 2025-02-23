package ntu.vinh.quanlychitieu.BLL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import ntu.vinh.quanlychitieu.DAL.DatabaseHelper;
import ntu.vinh.quanlychitieu.MODELS.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    private DatabaseHelper dbHelper;

    public TransactionRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("transactions", null, null, null, null, null, "date DESC");
        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex("id");
                int amountIndex = cursor.getColumnIndex("amount");
                int categoryIndex = cursor.getColumnIndex("category");
                int noteIndex = cursor.getColumnIndex("note");
                int dateIndex = cursor.getColumnIndex("date");

                if (idIndex >= 0 && amountIndex >= 0 && categoryIndex >= 0 && noteIndex >= 0 && dateIndex >= 0) {
                    int id = cursor.getInt(idIndex);
                    String amount = cursor.getString(amountIndex);
                    String category = cursor.getString(categoryIndex);
                    String note = cursor.getString(noteIndex);
                    String date = cursor.getString(dateIndex);
                    transactions.add(new Transaction(id, amount, category, note, date));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return transactions;
    }

    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(true, "transactions", new String[]{"category"}, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int categoryIndex = cursor.getColumnIndex("category");
                if (categoryIndex >= 0) {
                    String category = cursor.getString(categoryIndex);
                    categories.add(category);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return categories;
    }
}