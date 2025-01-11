package ntu.vinh.quanlychitieu.BLL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ntu.vinh.quanlychitieu.DAL.TransactionDatabaseHelper;
import ntu.vinh.quanlychitieu.MODELS.Transaction;

public class TransactionRepository {
    private TransactionDatabaseHelper dbHelper;

    public TransactionRepository(Context context) {
        dbHelper = new TransactionDatabaseHelper(context);
    }
    public void addCategory(String categoryName) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TransactionDatabaseHelper.COLUMN_CATEGORY_NAME, categoryName);

        db.insert(TransactionDatabaseHelper.TABLE_CATEGORIES, null, values);
        db.close();
    }


    public void addTransaction(double amount, String category, String note, String date) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TransactionDatabaseHelper.COLUMN_AMOUNT, amount);
        values.put(TransactionDatabaseHelper.COLUMN_CATEGORY, category);
        values.put(TransactionDatabaseHelper.COLUMN_NOTE, note);
        values.put(TransactionDatabaseHelper.COLUMN_DATE, date);

        db.insert(TransactionDatabaseHelper.TABLE_TRANSACTIONS, null, values);
        db.close();
    }
    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(TransactionDatabaseHelper.TABLE_CATEGORIES,
                new String[]{TransactionDatabaseHelper.COLUMN_CATEGORY_NAME},
                null, null, null, null, null);

        while (cursor.moveToNext()) {
            categories.add(cursor.getString(cursor.getColumnIndexOrThrow(TransactionDatabaseHelper.COLUMN_CATEGORY_NAME)));
        }

        cursor.close();
        db.close();
        return categories;
    }

}
