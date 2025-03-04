package com.example.watchit;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.example.watchit.R;
import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Integer> movieList;
    private ViewPager2 viewPager;

    public MovieAdapter(List<Integer> originalList, ViewPager2 viewPager) {
        this.viewPager = viewPager;
        this.movieList = new ArrayList<>();

        // Infinite scrolling setup (Adding duplicate first and last items)
        this.movieList.add(originalList.get(originalList.size() - 1)); // Fake last as first
        this.movieList.addAll(originalList); // Actual images
        this.movieList.add(originalList.get(0)); // Fake first as last
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.imageView.setImageResource(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.movieImage);
        }
    }
}
