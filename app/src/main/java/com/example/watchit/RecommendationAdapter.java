package com.example.watchit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class RecommendationAdapter extends RecyclerView.Adapter<RecommendationAdapter.RecommendationViewHolder> {
    private Context context;
    private List<RecommendationModel> recommendationList;

    public RecommendationAdapter(Context context, List<RecommendationModel> recommendationList) {
        this.context = context;
        this.recommendationList = recommendationList;
    }

    @NonNull
    @Override
    public RecommendationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommendation, parent, false);
        return new RecommendationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendationViewHolder holder, int position) {
        RecommendationModel recommendation = recommendationList.get(position);
        holder.title.setText(recommendation.getTitle());
        holder.rating.setText("⭐ " + recommendation.getRating());

        Glide.with(context)
                .load(recommendation.getPosterUrl())
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return recommendationList.size();
    }

    public static class RecommendationViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView title, rating;

        public RecommendationViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.movie_poster);
            title = itemView.findViewById(R.id.movie_title);
            rating = itemView.findViewById(R.id.movie_rating);
        }
    }
}
