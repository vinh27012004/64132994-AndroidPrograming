package th.phamnguyentrivinh.chuyenmanhinh_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEnglish = findViewById(R.id.btnEnglish);
        Button btnMath = findViewById(R.id.btnMath);
        Button btnProgramming = findViewById(R.id.btnProgramming);



        btnEnglish.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EnglishActivity.class);
                startActivity(intent);
        });


        btnMath.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MathActivity.class);
            startActivity(intent);
        });


        btnProgramming.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProgrammingActivity.class);
            startActivity(intent);
        });


    }
}