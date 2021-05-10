package com.example.combinator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class HighScoreAdapter extends RecyclerView.Adapter<HighScoreAdapter.ViewHolder> {
    private List<HighScore> dataSet;

    public HighScoreAdapter(List<HighScore> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.high_score_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getHighScoreName().setText(dataSet.get(position).getName());
        holder.getHighScore().setText(String.valueOf(dataSet.get(position).getScore()));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView highScoreName;
        private final TextView highScore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            highScoreName = itemView.findViewById(R.id.high_score_name);
            highScore = itemView.findViewById(R.id.high_score);
        }

        public TextView getHighScoreName() {
            return highScoreName;
        }

        public TextView getHighScore() {
            return highScore;
        }
    }
}
