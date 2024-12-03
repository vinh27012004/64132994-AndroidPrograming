package ntu.vinh.finalprojecttracking;

public class Notification {
    private int id;
    private String title;
    private String description;
    private long time;

    // Constructors, getters and setters
    public Notification(int id, String title, String description, long time) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.time = time;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
