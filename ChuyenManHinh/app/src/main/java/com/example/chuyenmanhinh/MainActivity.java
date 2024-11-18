package com.example.chuyenmanhinh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }
    // Chuyen sang man hinh 2
    public void Chuyensangmanhinh2(View v){
        Intent iMH2 = new Intent(this, MainActivity2.class);

        iMH2.putExtra("ten", "vinh");

        startActivity(iMH2);
    }
}