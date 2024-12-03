package th.phamnguyentrivinh.pheptoan;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private EditText edtNumberA, edtNumberB;
    private RadioGroup radioGroupOperations;
    private Button btnCalculate;
    private TextView tvResult;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtNumberA = findViewById(R.id.edtNumberA);
        edtNumberB = findViewById(R.id.edtNumberB);
        radioGroupOperations = findViewById(R.id.radioGroupOperations);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        btnCalculate.setOnClickListener(v -> {
            String strA = edtNumberA.getText().toString();
            String strB = edtNumberB.getText().toString();

            if (strA.isEmpty() || strB.isEmpty()) {
                Toast.makeText(MainActivity.this, "Vui lòng nhập đủ hai số", Toast.LENGTH_SHORT).show();
                return;
            }

            double numA = Double.parseDouble(strA);
            double numB = Double.parseDouble(strB);
            double result = 0;

            // Lấy phép toán được chọn
            int selectedId = radioGroupOperations.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(MainActivity.this, "Vui lòng chọn phép toán", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedOperation = findViewById(selectedId);
            String operation = selectedOperation.getText().toString();

            // Xử lý phép toán
            switch (operation) {
                case "+":
                    result = numA + numB;
                    break;
                case "-":
                    result = numA - numB;
                    break;
                case "*":
                    result = numA * numB;
                    break;
                case "/":
                    if (numB == 0) {
                        Toast.makeText(MainActivity.this, "Không thể chia cho 0", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    result = numA / numB;
                    break;
            }

            tvResult.setText("Kết quả: " + result);
        });
    }
}

