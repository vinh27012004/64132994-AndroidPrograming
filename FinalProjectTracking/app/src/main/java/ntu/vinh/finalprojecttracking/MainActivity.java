package ntu.vinh.finalprojecttracking;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.Manifest;
import android.content.pm.PackageManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_SCHEDULE_EXACT_ALARM = 1;
    private EditText titleEditText;
    private EditText descriptionEditText;
    private Button addNotificationButton;
    private RecyclerView notificationsRecyclerView;
    private NotificationAdapter notificationAdapter;
    private List<Notification> notificationList;
    private EditText timeEditText;
    private EditText dateEditText;
    private Calendar selectedDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SCHEDULE_EXACT_ALARM) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SCHEDULE_EXACT_ALARM}, REQUEST_CODE_SCHEDULE_EXACT_ALARM);
        }
        timeEditText = findViewById(R.id.timeEditText);
        dateEditText = findViewById(R.id.dateEditText);
        selectedDateTime = Calendar.getInstance();
        //Lắng nghe sự kiện nhấn vào ô ngày
        dateEditText.setOnClickListener(v -> showDatePickerDialog());
        //Lắng nghe sự kiện nhấn vào ô giờ
        timeEditText.setOnClickListener(v -> showTimePickerDialog());

        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        addNotificationButton = findViewById(R.id.addNotificationButton);
        notificationsRecyclerView = findViewById(R.id.notificationsRecyclerView);

        notificationList = new ArrayList<>();
        notificationAdapter = new NotificationAdapter(notificationList);
        notificationsRecyclerView.setAdapter(notificationAdapter);
        notificationsRecyclerView.setLayoutManager(new LinearLayoutManager(this));



        addNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotification();
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_SCHEDULE_EXACT_ALARM) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
            } else {
                Toast.makeText(this, "Permission denied to schedule exact alarms", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void addNotification() {
        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();

        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Hãy nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        // Thêm thông báo mới vào danh sách
        Notification notification = new Notification(notificationList.size(), title, description, Calendar.getInstance().getTimeInMillis());
        notificationList.add(notification);
        notificationAdapter.notifyDataSetChanged();

        // Thiết lập thông báo
        setAlarm(notification);

        // Xóa nội dung trong các trường nhập liệu
        titleEditText.setText("");
        descriptionEditText.setText("");

        Toast.makeText(this, "Đã thêm thông báo", Toast.LENGTH_SHORT).show();
    }

    private void setAlarm(Notification notification) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("Tiêu để", notification.getTitle());
        intent.putExtra("Mô tả", notification.getDescription());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, notification.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // Set the alarm to trigger after 5 seconds for testing
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis() + 5000, pendingIntent);
    }
}

