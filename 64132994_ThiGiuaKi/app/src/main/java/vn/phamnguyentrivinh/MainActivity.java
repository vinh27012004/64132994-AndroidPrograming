package vn.phamnguyentrivinh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    public void BackMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void Cau1(View v){
        Intent iCau1 = new Intent(this, activityCau1.class);
        startActivity(iCau1);
    }

    public void Cau2(View v){
        Intent iCau2 = new Intent(this, activityCau2.class);
        startActivity(iCau2);
    }
    public void Cau3(View v){
        Intent iCau3 = new Intent(this, activityCau3.class);
        startActivity(iCau3);
    }

    public void Cau4(View v){
        Intent iCau4 = new Intent(this, activityCau4.class);
        startActivity(iCau4);
    }
}