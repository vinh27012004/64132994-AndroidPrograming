package ntu.vinh.quanlychitieu.MODELS;

    public class Transaction {
    private int id;
    private double amount;
    private String category;
    private String note;
    private String date;

    public Transaction(int id, double amount, String category, String note, String date) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.note = note;
        this.date = date;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getNote() {
        return note;
    }

    public String getDate() {
        return date;
    }
}
