package com.example.watchit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class upcoming_movie_adapter extends RecyclerView.Adapter<upcoming_movie_adapter.MovieViewHolder> {
    private List<Model_Movie1> movieList;

    public upcoming_movie_adapter(List<Model_Movie1> movieList) {
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
        Model_Movie1 movie = movieList.get(position);
        holder.imageView.setImageResource(movie.getImageResId());
        holder.movieName.setText(movie.getMovieName());  // Set movie name
    }

    @Override
    public int getItemCount() {
        return movieList.size();
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
