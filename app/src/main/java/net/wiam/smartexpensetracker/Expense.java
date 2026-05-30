package net.wiam.smartexpensetracker;


public class Expense {

    String category;
    String amount;
    String date;

    public Expense(String category, String amount, String date) {
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
}