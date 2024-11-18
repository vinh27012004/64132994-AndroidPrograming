package vn.phamnguyentrivinh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activityCau1 extends AppCompatActivity {
    public void BackMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText inputA = findViewById(R.id.input_a);
        EditText inputB = findViewById(R.id.input_b);
        Button buttonSum = findViewById(R.id.button_sum);
        EditText result = findViewById(R.id.result);

        buttonSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aStr = inputA.getText().toString();
                String bStr = inputB.getText().toString();

                if (!aStr.isEmpty() && !bStr.isEmpty()) {
                    int a = Integer.parseInt(aStr);
                    int b = Integer.parseInt(bStr);
                    int sum = a + b;
                    result.setText("Kết quả: " + sum);
                } else {
                    result.setText("Vui lòng nhập cả hai số");
                }
            }
        });
    }
}