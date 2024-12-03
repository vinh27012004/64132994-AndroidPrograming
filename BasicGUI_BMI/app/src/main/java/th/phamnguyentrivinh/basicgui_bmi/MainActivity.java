package th.phamnguyentrivinh.basicgui_bmi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText edtWeight, edtHeight;
    private CheckBox checkBoxAsian;
    private Button btnCalculate;
    private TextView tvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtWeight = findViewById(R.id.edtWeight);
        checkBoxAsian = findViewById(R.id.checkBoxAsian);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);


        btnCalculate.setOnClickListener(v -> {

            String strWeight = edtWeight.getText().toString();
            String strHeight = edtHeight.getText().toString();


            if (strWeight.isEmpty() || strHeight.isEmpty()) {
                Toast.makeText(MainActivity.this, "Vui lòng nhập đủ chiều cao và cân nặng", Toast.LENGTH_SHORT).show();
                return;
            }

            double weight = Double.parseDouble(strWeight);
            double height = Double.parseDouble(strHeight);

            if(weight <= 0 || height<= 0){
                Toast.makeText(MainActivity.this, "Vui lòng nhập số lớn hơn 0", Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }
}