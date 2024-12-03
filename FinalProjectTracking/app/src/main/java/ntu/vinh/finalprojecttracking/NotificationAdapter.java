package ntu.vinh.finalprojecttracking;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

// Class representing the adapter for the RecyclerView
public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    private List<Notification> notifications;

    // Constructor for the adapter
    public NotificationAdapter(List<Notification> notifications) {
        this.notifications = notifications;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        return new NotificationViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        // Get element from your dataset at this position and replace the contents of the view with that element
        Notification notification = notifications.get(position);
        holder.titleTextView.setText(notification.getTitle());
        holder.descriptionTextView.setText(notification.getDescription());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return notifications.size();
    }

    // Provide a reference to the type of views that you are using (custom ViewHolder)
    public static class NotificationViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final TextView descriptionTextView;

        public NotificationViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            titleTextView = view.findViewById(R.id.notificationTitleTextView);
            descriptionTextView = view.findViewById(R.id.notificationDescriptionTextView);
        }
    }
}
