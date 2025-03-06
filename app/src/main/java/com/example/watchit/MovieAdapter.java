package com.example.watchit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<?> movieList; // Allow both Model_movie and Integer lists
    private ViewPager2 viewPager;
    private boolean isIntegerList; // Flag to differentiate types

    public MovieAdapter(List<?> movieList, ViewPager2 viewPager) {
        this.movieList = movieList;
        this.viewPager = viewPager;
        this.isIntegerList = !movieList.isEmpty() && movieList.get(0) instanceof Integer;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        if (isIntegerList) {
            // Handle Integer list (image resources)
            holder.imageView.setImageResource((Integer) movieList.get(position));
//            holder.movieName.setVisibility(View.GONE); // Hide movie name for banners
        } else {
            // Handle Model_movie list
            Model_movie movie = (Model_movie) movieList.get(position);
            holder.imageView.setImageResource(movie.getImageResId());
//            holder.movieName.setText(movie.getMovieName());
//            holder.movieName.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
//        TextView movieName;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.movieImage);

        }
    }
}
