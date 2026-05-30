package net.wiam.smartexpensetracker;

import java.util.ArrayList;

public class ExpenseData {
    String category;
    String amount;
    String date;

    public static ArrayList<Expense> expenseList = new ArrayList<>();

    public ExpenseData(String category, String amount, String date) {
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

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
