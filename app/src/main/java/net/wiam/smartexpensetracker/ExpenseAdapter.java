package net.wiam.smartexpensetracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {

    Context context;
    ArrayList<ExpenseData> expenseList;

    public ExpenseAdapter(Context context, ArrayList<ExpenseData> expenseList) {
        this.context = context;
        this.expenseList = expenseList;
    }

    @NonNull
    @Override
    public ExpenseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_expense, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.ViewHolder holder, int position) {

        ExpenseData expense = expenseList.get(position);

        holder.tvCategory.setText(expense.getCategory());
        holder.tvAmount.setText(expense.getAmount());
        holder.tvDate.setText(expense.getDate());

        holder.imageView.setImageResource(R.drawable.logo);

        holder.btnDetails.setOnClickListener(v -> {

            Intent intent = new Intent(context, ExpenseDetails.class);

            intent.putExtra("category", expense.getCategory());
            intent.putExtra("amount", expense.getAmount());
            intent.putExtra("date", expense.getDate());

            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCategory, tvAmount, tvDate;
        ImageView imageView;
        Button btnDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCategory = itemView.findViewById(R.id.tvItemCategory);
            tvAmount = itemView.findViewById(R.id.tvItemAmount);
            tvDate = itemView.findViewById(R.id.tvItemDate);

            imageView = itemView.findViewById(R.id.imageView);

            btnDetails = itemView.findViewById(R.id.btnDetails);
        }
    }
}