package vn.phamnguyentrivinh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activityCau2 extends AppCompatActivity {
    public void BackMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView phoneListView = findViewById(R.id.list_view);

        // Sample data
        String[] phones = {"iPhone 13", "Samsung Galaxy S21", "Google Pixel 6", "OnePlus 9", "Sony Xperia 5"};

        // Create an ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_phone, phones);

        // Set the adapter to the ListView
        phoneListView.setAdapter(adapter);
    }
}