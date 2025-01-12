package ntu.vinh.quanlychitieu.BLL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import ntu.vinh.quanlychitieu.DAL.DatabaseHelper;
import ntu.vinh.quanlychitieu.MODELS.Category;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    private DatabaseHelper dbHelper;

    public CategoryRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void addCategory(Category category) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", category.getName());
        db.insert("categories", null, values);
        db.close();
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("categories", null, null, null, null, null, "name ASC");
        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex("id");
                int nameIndex = cursor.getColumnIndex("name");

                if (idIndex >= 0 && nameIndex >= 0) {
                    int id = cursor.getInt(idIndex);
                    String name = cursor.getString(nameIndex);
                    categories.add(new Category(id, name));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return categories;
    }
}