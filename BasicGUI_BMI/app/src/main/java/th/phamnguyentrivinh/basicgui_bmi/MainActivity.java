package th.phamnguyentrivinh.basicgui_bmi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private EditText edtHeight, edtWeight;
    private CheckBox checkBoxAsian;
    private Button btnCalculate;
    private TextView tvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtHeight = findViewById(R.id.edtHeight);
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

            double bmi = weight / (height * height);
            boolean checkAsian = checkBoxAsian.isChecked();
            String result = "";

            if(checkAsian){
                if(bmi < 17.5) {
                    result = "Bạn đang gầy";
                } else if(bmi >= 17.5 && bmi <= 22.99) {
                    result = "Bạn đang bình thường";
                } else if(bmi >= 23 && bmi <= 27.99) {
                    result = "Bạn đang thừa cân";
                } else {
                    result = "Bạn đang béo phì";
                }
            } else {
                if (bmi < 18.5) {
                    result = "Bạn đang gầy";
                } else if (bmi >= 18.5 && bmi <= 24.99) {
                    result = "Bạn đang bình thường";
                } else if (bmi >= 23 && bmi <= 27.99) {
                    result = "Bạn đang thừa cân";
                } else {
                    result = "Bạn đang béo phì";
                }
            }
            tvResult.setText(String.format("Kết quả:\nBMI = %.2f\nĐánh giá: %s", bmi, result));
        });
    }
}