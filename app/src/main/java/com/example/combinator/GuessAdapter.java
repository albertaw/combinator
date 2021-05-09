package com.example.combinator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GuessAdapter extends RecyclerView.Adapter<GuessAdapter.ViewHolder> {
    private List<Guess> dataSet;

    public GuessAdapter(List<Guess> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guess_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getCombo().setText(dataSet.get(position).getCombo());
        holder.getFeedback().setText(dataSet.get(position).getFeedback());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView combo;
        private final TextView feedback;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            combo = itemView.findViewById(R.id.guess_combo);
            feedback = itemView.findViewById(R.id.guess_feedback);
        }

        public TextView getCombo() {
            return combo;
        }

        public TextView getFeedback() {
            return feedback;
        }
    }
}
