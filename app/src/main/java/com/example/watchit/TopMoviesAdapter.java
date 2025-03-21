package com.example.watchit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TopMoviesAdapter extends RecyclerView.Adapter<TopMoviesAdapter.MovieViewHolder> {
    private List<Model_Movie1> movieList;

    public TopMoviesAdapter(List<Model_Movie1> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_top_movies_dashboard, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        // Get the real position using modulo
        int realPosition = position % movieList.size();
        Model_Movie1 movie = movieList.get(realPosition);

        holder.imageView.setImageResource(movie.getImageResId());
        holder.movieName.setText(movie.getMovieName());
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE; // Infinite loop
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView movieName;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.movieImage1);
            movieName = itemView.findViewById(R.id.movieName);
        }
    }
}
