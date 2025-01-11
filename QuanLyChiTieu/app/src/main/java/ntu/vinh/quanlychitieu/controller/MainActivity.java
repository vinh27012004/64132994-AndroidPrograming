package ntu.vinh.quanlychitieu.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.widget.PopupMenu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ntu.vinh.quanlychitieu.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FloatingActionButton fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(MainActivity.this, v);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.menu_add_options, popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.menu_add_transaction) {
                    Intent addTransactionIntent = new Intent(MainActivity.this, AddTransactionActivity.class);
                    startActivity(addTransactionIntent);
                    return true;
                } else if (itemId == R.id.menu_add_category) {
                    Intent addCategoryIntent = new Intent(MainActivity.this, AddCategoryActivity.class);
                    startActivity(addCategoryIntent);
                    return true;
                } else {
                    return false;
                }
            });
            popup.show();
        });
    }
}