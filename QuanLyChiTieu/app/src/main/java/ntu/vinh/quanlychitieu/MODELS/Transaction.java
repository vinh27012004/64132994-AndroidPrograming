package ntu.vinh.quanlychitieu.MODELS;

public class Transaction {
    private int id;
    private String amount;
    private String category;
    private String note;
    private String date;

    public Transaction(int id, String amount, String category, String note, String date) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.note = note;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getAmount() {
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