package com.example.chuyenmanhinh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
    }

    public void Chuyensangmanhinh1(View v) {
        Intent iNhanDuoc = getIntent();

        String data = iNhanDuoc.getStringExtra("ten");

        Toast.makeText(getBaseContext(), data , Toast.LENGTH_LONG).show();

        Intent iManHinhChinh = new Intent(MainActivity2.this, MainActivity.class);

        startActivity(iManHinhChinh);
    }
}