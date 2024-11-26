package ntu.vinh.finalprojecttracking;

public class Notification {
    private int id;
    private String title;
    private String description;
    private long time;

    public Notification() {
    }

    public Notification(long time, String description, String title, int id) {
        this.time = time;
        this.description = description;
        this.title = title;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
