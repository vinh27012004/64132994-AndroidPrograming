package ntu.vinh.finalprojecttracking;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Đã đến giờ học!", Toast.LENGTH_LONG).show();
    }
}
