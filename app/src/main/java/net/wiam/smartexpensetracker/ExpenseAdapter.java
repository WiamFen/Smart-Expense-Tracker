package net.wiam.smartexpensetracker;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {

    ArrayList<Expense> list;

    public ExpenseAdapter(ArrayList<Expense> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_expense, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Expense expense = list.get(position);

        holder.tvCategory.setText(expense.getCategory());
        holder.tvAmount.setText(expense.getAmount() + " DH");
        holder.tvDate.setText(expense.getDate());

        // CLICK DETAILS
        holder.btnDetails.setOnClickListener(v -> {

            Intent intent = new Intent(v.getContext(), ExpenseDetails.class);

            intent.putExtra("category", expense.getCategory());
            intent.putExtra("amount", expense.getAmount());
            intent.putExtra("date", expense.getDate());

            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCategory, tvAmount, tvDate;
        Button btnDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCategory = itemView.findViewById(R.id.tvItemCategory);
            tvAmount = itemView.findViewById(R.id.tvItemAmount);
            tvDate = itemView.findViewById(R.id.tvItemDate);
            btnDetails = itemView.findViewById(R.id.btnDetails);
        }
    }
}